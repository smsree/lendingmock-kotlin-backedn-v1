package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.VehicleLoanEvent
import com.leadingmock.loanconsumer.service.VehicleLoanEventControllerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/vehicleLoan")
@CrossOrigin
class VehicleLoanEventController {
    @Autowired
    private val vehicleLoanService: VehicleLoanEventControllerService? = null
    @GetMapping("/")
    fun welcomeMessage(): Mono<String> {
        return Mono.just("Welcome to axis bank vehicle loan section")
    }

    @GetMapping("/allVehicleLoan")
    fun getAllVehicleLoan(): Flux<VehicleLoanEvent?>? {
        return vehicleLoanService?.getAllVehicleLoan()
    }

    @GetMapping("/{id}")
    fun getVehicleLoanById(@PathVariable("id") id: String?): Mono<ResponseEntity<VehicleLoanEvent>>? {
        return vehicleLoanService?.getVehicleLoanById(id)
            ?.map { vehicleLoan -> ResponseEntity.ok().body(vehicleLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @GetMapping("/customerMobileNo/{mobileNo}")
    fun getVehicleLoanByMobileNo(@PathVariable("mobileNo") mobileNo: Long?): Mono<ResponseEntity<VehicleLoanEvent>>? {
        return vehicleLoanService?.getVehicleLoanByMobileNo(mobileNo)
            ?.map { vehicleLoan -> ResponseEntity.ok().body(vehicleLoan) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExistingLoanById(@PathVariable("id") id: String?): Mono<Void>? {
        return vehicleLoanService?.deleteVehicleLoanById(id)
    }
}
