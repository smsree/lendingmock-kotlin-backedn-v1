package com.leadingmock.loanconsumer.consumerService

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanconsumer.domain.HousingLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.repository.HousingLoanRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HousingLoanConsumerService {
    @Autowired
    var housingLoanRepository: HousingLoanRepository? = null
    var objectMapper = ObjectMapper()
    @Throws(JsonProcessingException::class)
    fun processHousingLoanEventPostAndPut(consumerRecord: ConsumerRecord<String?, String?>) {
        val housingLoanEvent: HousingLoanEvent =
            objectMapper.readValue(consumerRecord.value(), HousingLoanEvent::class.java)
        println("housing loan:{}"+ housingLoanEvent)
        when (housingLoanEvent.getLoanEventType()) {
            LoanEventType.NEW -> save(housingLoanEvent)
            LoanEventType.UPDATE -> {
                validate(housingLoanEvent)
                save(housingLoanEvent)
            }

            else -> println("not a valid housing loan event")
        }
    }

    private fun validate(housingLoanEvent: HousingLoanEvent) {
        try {
            val voidMono = housingLoanRepository!!.deleteById(housingLoanEvent.getHousingLoanId()!!)
            voidMono.subscribe(
                { k: Void? -> println("old record deleted") },
                { e: Throwable -> println(e.message) }
            ) { println("validation completed") }
            println("Validation done {}"+ housingLoanEvent)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun save(housingLoanEvent: HousingLoanEvent) {
        val save = housingLoanRepository!!.save(housingLoanEvent)
        save.subscribe(
            { housingLoanEvent1: HousingLoanEvent? ->
                println(
                    housingLoanEvent1
                )
            },
            { e: Throwable -> println(e.message) }
        ) { println("Completed the task") }
        println("successfully saved housing loan message to database:{}"+ housingLoanEvent)
    }
}
