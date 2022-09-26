package com.leadingmock.loanproducer.controller

import com.leadingmock.loanproducer.domain.HousingLoanEvent
import com.leadingmock.loanproducer.domain.LoanEventType
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //to avoid port conflicts
@EmbeddedKafka(topics = ["housing-loan"], partitions = 1)
@TestPropertySource(properties = ["spring.kafka.producer.bootstrap-servers=\${spring.embedded.kafka.brokers}", "spring.kafka.admin.properties.bootstrap.servers=\${spring.embedded.kafka.brokers}"])
class HousingLoanControllerIntegrationTest {
    @Autowired
    var testRestTemplate: TestRestTemplate? = null

    @Autowired
    var embeddedKafkaBroker: EmbeddedKafkaBroker? = null
    private var consumer: Consumer<String, String>? = null
    @BeforeEach
    fun setUp() {
        val configs: Map<String, Any> = HashMap(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker))
        consumer = DefaultKafkaConsumerFactory(configs, StringDeserializer(), StringDeserializer()).createConsumer()
        embeddedKafkaBroker!!.consumeFromAllEmbeddedTopics(consumer)
    }

    @AfterEach
    fun tearDown() {
        consumer!!.close()
    }

    @Test
    fun postHousingLoanEventToBroker() {
        val housingLoanEvent = HousingLoanEvent("id", 1L, "add", "housing loan", 100, 2.2, "APPLIED", LoanEventType.NEW)
        val headers = HttpHeaders()
        headers["content-type"] = MediaType.APPLICATION_JSON.toString()
        val request: HttpEntity<HousingLoanEvent> = HttpEntity<HousingLoanEvent>(housingLoanEvent, headers)
        val exchange = testRestTemplate!!.exchange(
            "/v1/housingLoan/", HttpMethod.POST, request,
            String::class.java
        )
        Assertions.assertEquals(HttpStatus.CREATED, exchange.statusCode)
        val singleRecord = KafkaTestUtils.getSingleRecord(consumer, "housing-loan")
        val value = singleRecord.value()
        val expected =
            "{\"housingLoanId\":\"id\",\"customerMobileNo\":1,\"address\":\"add\",\"loanName\":\"housing loan\",\"loanamount\":100,\"rateOfInterest\":2.2,\"status\":\"APPLIED\",\"loanEventType\":\"NEW\"}"
        Assertions.assertEquals(expected, value)
    }

    @Test
    fun postHousingLoanEventTest() {
        val housingLoanEvent = HousingLoanEvent("id", 1L, "add", "housing loan", 100, 2.2, "APPLIED", LoanEventType.NEW)
        val headers = HttpHeaders()
        headers["content-type"] = MediaType.APPLICATION_JSON.toString()
        val request: HttpEntity<HousingLoanEvent> = HttpEntity<HousingLoanEvent>(housingLoanEvent, headers)
        val exchange = testRestTemplate!!.exchange(
            "/v1/housingLoan/", HttpMethod.PUT, request,
            String::class.java
        )
        Assertions.assertEquals(HttpStatus.OK, exchange.statusCode)
        val singleRecord = KafkaTestUtils.getSingleRecord(consumer, "housing-loan")
        val value = singleRecord.value()
        val expected =
            "{\"housingLoanId\":\"id\",\"customerMobileNo\":1,\"address\":\"add\",\"loanName\":\"housing loan\",\"loanamount\":100,\"rateOfInterest\":2.2,\"status\":\"APPLIED\",\"loanEventType\":\"UPDATE\"}"
        Assertions.assertEquals(expected, value)
    }
}
