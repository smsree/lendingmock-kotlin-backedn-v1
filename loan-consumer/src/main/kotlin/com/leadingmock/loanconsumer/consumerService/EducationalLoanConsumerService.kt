package com.leadingmock.loanconsumer.consumerService

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanconsumer.domain.EducationalLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.repository.EducationalLoanRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EducationalLoanConsumerService {
    @Autowired
    var educationalLoanRepository: EducationalLoanRepository? = null
    var objectMapper = ObjectMapper()
    @Throws(JsonProcessingException::class)
    fun processEducationalLoanEventsPostAndPut(consumerRecord: ConsumerRecord<String?, String?>) {
        val educationalLoanEvent: EducationalLoanEvent = objectMapper.readValue(
            consumerRecord.value(),
            EducationalLoanEvent::class.java
        )
        println("Educational loan : {}"+ educationalLoanEvent)
        when (educationalLoanEvent.getLoanEventType()) {
            LoanEventType.NEW -> save(educationalLoanEvent)
            LoanEventType.UPDATE -> {
                validate(educationalLoanEvent)
                save(educationalLoanEvent)
            }

            else -> println("invalid educational loan event type")
        }
    }

    private fun validate(educationalLoanEvent: EducationalLoanEvent) {
        try {
            val voidMono = educationalLoanRepository!!.deleteById(educationalLoanEvent.getEducationalLoanId()!!)
            voidMono.subscribe(
                { k: Void? -> println("old record deleted") },
                { e: Throwable -> println(e.message) }
            ) { println("validation completed") }
            println("Validation done {}"+ educationalLoanEvent)
        } catch (e: Exception) {
            println(e.message)
        }
        println("Validation done for educational loan {}"+ educationalLoanEvent)
    }

    private fun save(educationalLoanEvent: EducationalLoanEvent) {
        val save = educationalLoanRepository!!.save(educationalLoanEvent)
        save.subscribe(
            { educationalLoanEvent1: EducationalLoanEvent? ->
                println(
                    educationalLoanEvent1
                )
            },
            { e: Throwable -> println(e.message) }
        ) { println("Completed the task of educational loan event") }
        println("successfully saved educational loan message to database:{}"+ educationalLoanEvent)
    }
}
