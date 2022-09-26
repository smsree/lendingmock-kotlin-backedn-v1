package com.leadingmock.loanconsumer.repository

import com.leadingmock.loanconsumer.domain.BusinessLoanEvent
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface BusinessLoanRepository : ReactiveMongoRepository<BusinessLoanEvent?, String?> {
    fun findBusinessLoanByCustomerMobileNo(mobileNo: Long?): Mono<BusinessLoanEvent?>?
}