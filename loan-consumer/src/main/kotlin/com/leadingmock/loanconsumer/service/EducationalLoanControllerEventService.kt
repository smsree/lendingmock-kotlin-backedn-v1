package com.leadingmock.loanconsumer.service

import com.leadingmock.loanconsumer.domain.EducationalLoanEvent
import com.leadingmock.loanconsumer.repository.EducationalLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EducationalLoanControllerEventService {
    @Autowired
    private val educationalLoanRepository: EducationalLoanRepository? = null
    fun findAllEducationalLoan(): Flux<EducationalLoanEvent?> {
        return educationalLoanRepository?.findAll()!!
    }

    fun findEducationalLoanById(id: String?): Mono<EducationalLoanEvent?> {
        return educationalLoanRepository?.findById(id!!)!!
    }

    fun findEducationalLoanByMobileNo(mobileNo: Long?): Mono<EducationalLoanEvent?> {
        return educationalLoanRepository?.findEducationalLoanByCustomerMobileNo(mobileNo)!!
    }

    fun deleteEducationalLoanById(id: String?): Mono<Void> {
        return educationalLoanRepository!!.deleteById(id!!)
    }
}