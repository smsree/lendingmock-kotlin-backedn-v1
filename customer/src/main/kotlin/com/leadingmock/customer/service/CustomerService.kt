package com.leadingmock.customer.service

import com.leadingmock.customer.domain.Customer
import com.leadingmock.customer.dto.LoginDto
import com.leadingmock.customer.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CustomerService {


    @Autowired
    private val customerRepository:CustomerRepository? = null

    fun registerNewCustomer(customer: Customer?): Mono<Customer?>? {
        return customerRepository!!.save(customer!!);
    }

    fun validateLogin(login: LoginDto): Mono<Customer?>? {
        return customerRepository!!.findCustomerByPhoneNumber(login.getPhoneNumber())
            ?.filter{customer -> customer?.getDateOfBirth() == login.getDateOfBirth()};
    }

    fun findAllcustomer(): Flux<Customer?>? {
        return customerRepository!!.findAll()
    }

    fun deleteByCustomerId(customerId: String?): Mono<Void?>? {
        return customerRepository?.deleteById(customerId!!);
    }

}