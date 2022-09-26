package com.leadingmock.customer.controller

import com.leadingmock.customer.domain.Customer
import com.leadingmock.customer.dto.LoginDto
import com.leadingmock.customer.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/v1/customer")
@CrossOrigin
class CustomerController {

    @Autowired
    private var customerService:CustomerService? = null

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerNewCustomer(@RequestBody @Valid customer: Customer?): Mono<Customer?>?{
        return customerService?.registerNewCustomer(customer);
    }
    @PostMapping("/login")
    fun validateLogin(@RequestBody  @Valid login:LoginDto):Mono<ResponseEntity<Customer?>?>?{

       return customerService?.validateLogin(login)
            ?.map { customer->ResponseEntity.ok().body(customer) }
            ?.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))

    }
    @GetMapping()
    fun getAllCustomer(): Flux<Customer?>?{
        return customerService!!.findAllcustomer();
    }
    @DeleteMapping("/delete/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable("customerId") customerId:String?):Mono<Void?>?{
        return customerService?.deleteByCustomerId(customerId);
    }
}