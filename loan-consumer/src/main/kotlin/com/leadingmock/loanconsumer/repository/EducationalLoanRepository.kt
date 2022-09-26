package com.leadingmock.loanconsumer.repository

import com.leadingmock.loanconsumer.domain.EducationalLoanEvent
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EducationalLoanRepository : ReactiveMongoRepository<EducationalLoanEvent?, String?> {
    fun findEducationalLoanByCustomerMobileNo(mobileNo: Long?): Mono<EducationalLoanEvent?>?
}
