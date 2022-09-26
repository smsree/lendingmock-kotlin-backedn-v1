package com.leadingmock.loanconsumer.repository

import com.leadingmock.loanconsumer.domain.VehicleLoanEvent
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface VehicleLoanRepository : ReactiveMongoRepository<VehicleLoanEvent?, String?> {
    fun findVehicleLoanByCustomerMobileNo(mobileNo: Long?): Mono<VehicleLoanEvent?>?
}