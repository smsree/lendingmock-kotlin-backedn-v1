package com.leadingmock.loanconsumer.service

import com.leadingmock.loanconsumer.domain.BusinessLoanEvent
import com.leadingmock.loanconsumer.repository.BusinessLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BusinessLoanEventControllerService {
    @Autowired
    private val businessLoanRepository: BusinessLoanRepository? = null
    fun getAllBusinessLoanAvailable(): Flux<BusinessLoanEvent?>? {
        return businessLoanRepository!!.findAll()
    }

    fun findBusinessLoanById(id: String?): Mono<BusinessLoanEvent?>? {
        return businessLoanRepository!!.findById(id!!)
    }

    fun findBusinessLoanByMobileNo(mobileNo: Long?): Mono<BusinessLoanEvent?>? {
        return businessLoanRepository!!.findBusinessLoanByCustomerMobileNo(mobileNo)
    }

    fun deleteBusinessLoanByIdService(id: String?): Mono<Void?>? {
        return businessLoanRepository!!.deleteById(id!!)
    }
}