package com.leadingmock.loanproducer.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanproducer.domain.HousingLoanEvent
import com.leadingmock.loanproducer.domain.LoanEventType
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
@RequestMapping("/v1/housingLoan")
@CrossOrigin
class HousingLoanController {
    @Autowired
    var kafkaTemplate: KafkaTemplate<String, String>? = null
    var objectMapper = ObjectMapper()
    var topic = "housing-loan"
    @PostMapping("/")
    @Throws(JsonProcessingException::class)
    fun postHousingLoanEventToBroker(@RequestBody @Valid housingLoanEvent: HousingLoanEvent?): ResponseEntity<String> {
        housingLoanEvent?.setStatus("APPLIED")
        housingLoanEvent?.setLoanEventType(LoanEventType.NEW)
        val key: String?= housingLoanEvent?.getHousingLoanId()
        val value = objectMapper.writeValueAsString(housingLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message (housing loan) and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue housing loan"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("housing loan sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.CREATED).body("housing loan Message sent successfully to the Broker")
    }

    @PutMapping("/")
    @Throws(JsonProcessingException::class)
    fun updateHousingLoanEventToBroker(@RequestBody housingLoanEvent: HousingLoanEvent): ResponseEntity<String> {
        if (housingLoanEvent.getHousingLoanId() == null) {
            return ResponseEntity("Need housing loan id to update", HttpStatus.BAD_REQUEST)
        }
        housingLoanEvent.setLoanEventType(LoanEventType.UPDATE)
        val key: String? = housingLoanEvent?.getHousingLoanId()
        val value = objectMapper.writeValueAsString(housingLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message (housing loan) and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue housing loan update"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("housing loan updated successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.OK).body("housing loan Message updated successfully to the Broker")
    }
}
