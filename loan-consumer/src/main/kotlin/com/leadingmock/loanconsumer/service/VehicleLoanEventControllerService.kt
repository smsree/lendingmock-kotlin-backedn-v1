package com.leadingmock.loanconsumer.service

import com.leadingmock.loanconsumer.domain.VehicleLoanEvent
import com.leadingmock.loanconsumer.repository.VehicleLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class VehicleLoanEventControllerService {
    @Autowired
    private val vehicleLoanRepository: VehicleLoanRepository? = null
    fun getVehicleLoanById(id: String?): Mono<VehicleLoanEvent?> {
        return vehicleLoanRepository?.findById(id!!)!!
    }

    fun getVehicleLoanByMobileNo(mobileNo: Long?): Mono<VehicleLoanEvent?> {
        return vehicleLoanRepository?.findVehicleLoanByCustomerMobileNo(mobileNo!!)!!
    }

    fun deleteVehicleLoanById(id: String?): Mono<Void> {
        return vehicleLoanRepository?.deleteById(id!!)!!
    }

    fun getAllVehicleLoan(): Flux<VehicleLoanEvent?>? {
        return vehicleLoanRepository!!.findAll()
    }
}
