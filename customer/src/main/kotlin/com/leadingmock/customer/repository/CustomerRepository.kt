package com.leadingmock.customer.repository

import com.leadingmock.customer.domain.Customer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CustomerRepository: ReactiveMongoRepository<Customer?, String?> {
     fun findCustomerByPhoneNumber(phoneNumber: Long?): Mono<Customer?>?
}