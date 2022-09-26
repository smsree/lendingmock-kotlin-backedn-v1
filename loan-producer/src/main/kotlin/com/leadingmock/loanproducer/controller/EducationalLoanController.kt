package com.leadingmock.loanproducer.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.leadingmock.loanproducer.domain.EducationalLoanEvent
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
@RequestMapping("/v1/educationalLoan")
@CrossOrigin
class EducationalLoanController {
    @Autowired
    var kafkaTemplate: KafkaTemplate<String, String>? = null
    var objectMapper = ObjectMapper()
    var topic = "educational-loan"
    @PostMapping("/")
    @Throws(JsonProcessingException::class)
    fun postNewEducationalLoan(@RequestBody @Valid educationalLoanEvent: EducationalLoanEvent?): ResponseEntity<String> {
        educationalLoanEvent?.setStatus("APPLIED")
        educationalLoanEvent?.setLoanEventType(LoanEventType.NEW)
        val value = objectMapper.writeValueAsString(educationalLoanEvent)
        val key: String? = educationalLoanEvent?.getEducationalLoanId()
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the message (educational loan) and the exception is {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error in onFailue educational loan"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Educational loan sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Educational loan Message sent successfully to the Broker")
    }

    @PutMapping("/")
    @Throws(JsonProcessingException::class)
    fun updateEducationalLoanEvent(@RequestBody educationalLoanEvent: EducationalLoanEvent): ResponseEntity<String> {
        if (educationalLoanEvent.getEducationalLoanId() == null) {
            return ResponseEntity("Educational loan id is needed to update", HttpStatus.BAD_REQUEST)
        }
        educationalLoanEvent.setLoanEventType(LoanEventType.UPDATE)
        val key: String? = educationalLoanEvent?.getEducationalLoanId()
        val value = objectMapper.writeValueAsString(educationalLoanEvent)
        val record = ProducerRecord(topic, key, value)
        val send = kafkaTemplate!!.send(record)
        send.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onFailure(ex: Throwable) {
                println("Error sending the educational message and the exception is put {}"+ ex.message)
                try {
                    throw ex
                } catch (e: Throwable) {
                    println("Error  put mapping in onFailue"+ e.message)
                }
            }

            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Educational Message sent successfully with value {}"+ result!!.producerRecord.value())
            }
        })
        return ResponseEntity.status(HttpStatus.OK)
            .body("Educational loan Update Message sent successfully to the Broker")
    }
}
