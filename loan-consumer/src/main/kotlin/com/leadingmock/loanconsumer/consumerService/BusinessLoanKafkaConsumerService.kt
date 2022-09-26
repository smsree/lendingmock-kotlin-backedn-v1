package com.leadingmock.loanconsumer.consumerService
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanconsumer.domain.BusinessLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.repository.BusinessLoanRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BusinessLoanKafkaConsumerService {
    @Autowired
    var businessLoanRepository: BusinessLoanRepository? = null
    var objectMapper = ObjectMapper()
    @Throws(JsonProcessingException::class)
    fun processBusinessLoanEventsPostAndPut(consumerRecord: ConsumerRecord<String?, String?>) {
        val businessLoanEvent: BusinessLoanEvent =
            objectMapper.readValue(consumerRecord.value(), BusinessLoanEvent::class.java)
        println("Business loan : {}"+ businessLoanEvent)
        when (businessLoanEvent.getLoanEventType()) {
            LoanEventType.NEW -> save(businessLoanEvent)
            LoanEventType.UPDATE -> {
                validate(businessLoanEvent)
                save(businessLoanEvent)
            }

            else -> println("invalid business loan event type")
        }
    }

    private fun validate(businessLoanEvent: BusinessLoanEvent) {
        try {
            val voidMono = businessLoanRepository!!.deleteById(businessLoanEvent.getBusinessLoanId()!!)
            voidMono.subscribe(
                { k: Void? -> println("old record deleted") },
                { e: Throwable -> println(e.message) }
            ) { println("validation completed") }
            println("Validation done {}"+ businessLoanEvent)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun save(businessLoanEvent: BusinessLoanEvent) {
        val save = businessLoanRepository!!.save(businessLoanEvent)
        save.subscribe(
            { businessLoanEvent1: BusinessLoanEvent? ->
                println(
                    businessLoanEvent1
                )
            },
            { e: Throwable -> println(e.message) }
        ) { println("Completed the task") }
        println("successfully saved business loan message to database:{}"+ businessLoanEvent)
    }
}
