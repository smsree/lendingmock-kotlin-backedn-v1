package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.BusinessLoanEvent
import com.leadingmock.loanconsumer.service.BusinessLoanEventControllerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/businessLoan")
@CrossOrigin
class BusinessLoanEventController {
    @Autowired
    private val businessLoanService: BusinessLoanEventControllerService? = null
    @GetMapping("/")
    fun welcomeMessage(): Mono<String> {
        return Mono.just("Welcome to the axis bank business loan section")
    }

    @get:GetMapping("/allBuisnessLoan")
    val allBusinessLoan: Flux<BusinessLoanEvent?>?
        get() = businessLoanService?.getAllBusinessLoanAvailable()

    @GetMapping("/{id}")
    fun getBusinessLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<BusinessLoanEvent>>? {
        return businessLoanService?.findBusinessLoanById(id)
            ?.map { businessLoan -> ResponseEntity.ok().body(businessLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getBusinessLoanByCustomerMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<BusinessLoanEvent>>? {
        return businessLoanService?.findBusinessLoanByMobileNo(mobileNo)
            ?.map { businessLoan -> ResponseEntity.ok().body(businessLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBusinessLoanById(@PathVariable("id") id: String?): Mono<Void?>? {
        return businessLoanService?.deleteBusinessLoanByIdService(id)
    }
}
