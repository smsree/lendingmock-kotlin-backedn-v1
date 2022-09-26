package com.leadingmock.loanproducer.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanproducer.domain.LoanEventType
import com.leadingmock.loanproducer.domain.VehicleLoanEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/vehicleLoan")
@CrossOrigin
class VehicleLoanController {
    @Autowired
    var kafkaTemplate: KafkaTemplate<String, String>? = null
    var objectMapper = ObjectMapper()
    var topic = "vehicle-loan"
    @PostMapping("/")
    @Throws(JsonProcessingException::class)
    fun postVehcileLoanEventToBroker(@RequestBody @Valid vehicleLoanEvent: VehicleLoanEvent?): ResponseEntity<String> {
        vehicleLoanEvent?.setStatus("APPLIED")
        vehicleLoanEvent?.setLoanEventType(LoanEventType.NEW)
        val value = objectMapper.writeValueAsString(vehicleLoanEvent)
        val key: String? = vehicleLoanEvent?.getVehicleLoanId()
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message (vehicle loan) and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue vehicle loan"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("vehicle loan sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.CREATED).body("vehicle loan Message sent successfully to the Broker")
    }

    @PutMapping("/")
    @Throws(JsonProcessingException::class)
    fun updateVehicelLoan(@RequestBody vehicleLoanEvent: VehicleLoanEvent): ResponseEntity<String> {
        if (vehicleLoanEvent.getVehicleLoanId() == null) {
            return ResponseEntity("Vehicle loan id is need to update", HttpStatus.BAD_REQUEST)
        }
        vehicleLoanEvent.setLoanEventType(LoanEventType.UPDATE)
        val key: String? = vehicleLoanEvent?.getVehicleLoanId()
        val value = objectMapper.writeValueAsString(vehicleLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the vehicle message and the exception is put {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error  put mapping in onFailue"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Vehicle loan Message sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.OK).body("vehicle loan Update Message sent successfully to the Broker")
    }
}
