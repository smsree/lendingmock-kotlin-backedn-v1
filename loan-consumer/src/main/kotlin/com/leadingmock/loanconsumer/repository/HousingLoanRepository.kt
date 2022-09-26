package com.leadingmock.loanconsumer.repository

import com.leadingmock.loanconsumer.domain.HousingLoanEvent
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface HousingLoanRepository : ReactiveMongoRepository<HousingLoanEvent?, String?> {
    fun findHousingLoanByCustomerMobileNo(mobileNo: Long?): Mono<HousingLoanEvent?>?
}