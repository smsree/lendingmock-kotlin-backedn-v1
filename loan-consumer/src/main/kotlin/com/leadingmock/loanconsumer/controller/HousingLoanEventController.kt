package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.HousingLoanEvent
import com.leadingmock.loanconsumer.service.HousingLoanControllerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/housingLoan")
@CrossOrigin
class HousingLoanEventController {
    @Autowired
    var housingLoanService: HousingLoanControllerService? = null
    @GetMapping("/")
    fun welcomeMessage(): Mono<String> {
        return Mono.just("Welcome to axis bank housing loan section")
    }

    @GetMapping("/allHousingLoan")
    fun getallHousingLoan(): Flux<HousingLoanEvent?> {
        return housingLoanService?.findAllHousingLoan()!!
    }

    @GetMapping("/{id}")
    fun findHousingLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<HousingLoanEvent>>? {
        return housingLoanService?.findHousingLoanById(id)
            ?.map { housingLoan -> ResponseEntity.ok().body(housingLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun findHousingLoanByCustomerMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<HousingLoanEvent>>? {
        return housingLoanService?.findHousingLoanByMobileNo(mobileNo)
            ?.map { housingLoan -> ResponseEntity.ok().body(housingLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHousingLoan(@PathVariable("id") id: String?): Mono<Void>? {
        return housingLoanService?.deleteHousingLoanById(id)
    }
}
