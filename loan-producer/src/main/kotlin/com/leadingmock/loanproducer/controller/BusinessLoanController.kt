package com.leadingmock.loanproducer.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanproducer.domain.BusinessLoanEvent
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
@RequestMapping("/v1/businessLoan")
@CrossOrigin
class BusinessLoanController {
    @Autowired
    var kafkaTemplate: KafkaTemplate<String, String>? = null
    var objectMapper = ObjectMapper()
    var topic = "business-loan"
    var onSuccess1: String? = null
    @PostMapping("/")
    @Throws(JsonProcessingException::class)
    fun postEventToKafka(@RequestBody @Valid businessLoanEvent: BusinessLoanEvent?): ResponseEntity<String> {
        businessLoanEvent?.setStatus("APPLIED")
        businessLoanEvent?.setLoanEventType(LoanEventType.NEW)
        val key: String? = businessLoanEvent?.getBusinessLoanId()
        val value = objectMapper.writeValueAsString(businessLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message (Business loan) and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue Business loan"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Business loan sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.CREATED).body("Business loan Message sent successfully to the Broker")
    }

    @PutMapping("/")
    @Throws(JsonProcessingException::class)
    fun updateExistingEventViaKafka(@RequestBody businessLoanEvent: BusinessLoanEvent): ResponseEntity<String> {
        if (businessLoanEvent.getBusinessLoanId() == null) {
            return ResponseEntity("Business loan id is needed to update", HttpStatus.BAD_REQUEST)
        }
        businessLoanEvent.setLoanEventType(LoanEventType.UPDATE)
        val key: String? = businessLoanEvent?.getBusinessLoanId()
        val value = objectMapper.writeValueAsString(businessLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Message sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.OK).body("Update Message sent successfully to the Broker")
    }
}
