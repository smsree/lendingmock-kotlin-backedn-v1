package com.leadingmock.loanconsumer.consumer

import com.fasterxml.jackson.core.JsonProcessingException
import com.leadingmock.loanconsumer.consumerService.BusinessLoanKafkaConsumerService
import com.leadingmock.loanconsumer.consumerService.EducationalLoanConsumerService
import com.leadingmock.loanconsumer.consumerService.HousingLoanConsumerService
import com.leadingmock.loanconsumer.consumerService.VehicleLoanConsumerService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LoanEventConsumer {
    @Autowired
    var businessLoanKafkaConsumerService: BusinessLoanKafkaConsumerService? = null

    @Autowired
    var educationalLoanConsumerService: EducationalLoanConsumerService? = null

    @Autowired
    var vehicleLoanConsumerService: VehicleLoanConsumerService? = null

    @Autowired
    var housingLoanConsumerService: HousingLoanConsumerService? = null
    @KafkaListener(topics = ["business-loan"])
    @Throws(JsonProcessingException::class)
    fun onMessage(consumerRecord: ConsumerRecord<String?, String?>) {
        println("business loan consumer:{}"+ consumerRecord.value())
        businessLoanKafkaConsumerService?.processBusinessLoanEventsPostAndPut(consumerRecord)
    }

    @KafkaListener(topics = ["educational-loan"])
    @Throws(JsonProcessingException::class)
    fun onMessageEducational(consumerRecord: ConsumerRecord<String?, String?>) {
        println("educational loan consumer:{}"+ consumerRecord.value())
        educationalLoanConsumerService?.processEducationalLoanEventsPostAndPut(consumerRecord)
    }

    @KafkaListener(topics = ["vehicle-loan"])
    @Throws(JsonProcessingException::class)
    fun onMessageVehicle(consumerRecord: ConsumerRecord<String?, String?>) {
        println("Vehicle loan consumer {}"+ consumerRecord.value())
        vehicleLoanConsumerService?.processVehicleLoanEventsPostAndPut(consumerRecord)
    }

    @KafkaListener(topics = ["housing-loan"])
    @Throws(JsonProcessingException::class)
    fun onMessageHousing(consumerRecord: ConsumerRecord<String?, String?>) {
        println("housing loan {}"+ consumerRecord.value())
        housingLoanConsumerService?.processHousingLoanEventPostAndPut(consumerRecord)
    }
}
