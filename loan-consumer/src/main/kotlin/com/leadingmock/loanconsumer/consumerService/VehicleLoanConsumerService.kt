package com.leadingmock.loanconsumer.consumerService

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.domain.VehicleLoanEvent
import com.leadingmock.loanconsumer.repository.VehicleLoanRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service

class VehicleLoanConsumerService {
    @Autowired
    var vehicleLoanRepository: VehicleLoanRepository? = null
    var objectMapper = ObjectMapper()
    @Throws(JsonProcessingException::class)
    fun processVehicleLoanEventsPostAndPut(consumerRecord: ConsumerRecord<String?, String?>) {
        val vehicleLoanEvent: VehicleLoanEvent =
            objectMapper.readValue(consumerRecord.value(), VehicleLoanEvent::class.java)
        println("vehicle loan : {}"+ vehicleLoanEvent)
        when (vehicleLoanEvent.getLoanEventType()) {
            LoanEventType.NEW -> save(vehicleLoanEvent)
            LoanEventType.UPDATE -> {
                validate(vehicleLoanEvent)
                save(vehicleLoanEvent)
            }

            else -> println("invalid vehicle loan event type")
        }
    }

    private fun validate(vehicleLoanEvent: VehicleLoanEvent) {
        try {
            val voidMono = vehicleLoanRepository!!.deleteById(vehicleLoanEvent.getVehicleLoanId()!!)
            voidMono.subscribe(
                { k: Void? -> println("old record deleted") },
                { e: Throwable -> println(e.message) }
            ) { println("validation completed") }
            println("Validation done {}"+ vehicleLoanEvent)
        } catch (e: Exception) {
            println(e.message)
        }
        println("Validation done for vehicle loan {}"+ vehicleLoanEvent)
    }

    private fun save(vehicleLoanEvent: VehicleLoanEvent) {
        val save = vehicleLoanRepository!!.save(vehicleLoanEvent)
        save.subscribe(
            { vehicleLoanEvent1: VehicleLoanEvent? ->
                println(
                    vehicleLoanEvent1
                )
            },
            { e: Throwable -> println(e.message) }
        ) { println("Completed the task") }
        println("successfully saved vehicle loan message to database:{}"+ vehicleLoanEvent)
    }
}
