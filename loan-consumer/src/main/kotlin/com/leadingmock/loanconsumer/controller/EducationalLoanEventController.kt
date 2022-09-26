package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.EducationalLoanEvent
import com.leadingmock.loanconsumer.service.EducationalLoanControllerEventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/educationalLoan")
@CrossOrigin
class EducationalLoanEventController {
    @Autowired
    var educationalLoanService: EducationalLoanControllerEventService? = null
    @GetMapping("/")
    fun welcomeMessage(): Mono<String> {
        return Mono.just("Welcome to the axis bank educational loan section")
    }

    @get:GetMapping("/allEducationalLoan")
    val allEducationalLoan: Flux<EducationalLoanEvent?>?
        get() = educationalLoanService?.findAllEducationalLoan()

    @GetMapping("/{id}")
    fun getEducationalLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<EducationalLoanEvent>>? {
        return educationalLoanService?.findEducationalLoanById(id)
            ?.map { educationalLoan -> ResponseEntity.ok().body(educationalLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getEducationalLoanByMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<EducationalLoanEvent>>? {
        return educationalLoanService?.findEducationalLoanByMobileNo(mobileNo)
            ?.map { educationalLoan -> ResponseEntity.ok().body(educationalLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEducationalLoanById(@PathVariable("id") id: String?): Mono<Void>? {
        return educationalLoanService?.deleteEducationalLoanById(id)
    }
}
