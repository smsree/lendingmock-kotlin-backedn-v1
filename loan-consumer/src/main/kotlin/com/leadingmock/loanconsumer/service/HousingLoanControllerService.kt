package com.leadingmock.loanconsumer.service

import com.leadingmock.loanconsumer.domain.HousingLoanEvent
import com.leadingmock.loanconsumer.repository.HousingLoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class HousingLoanControllerService {
    @Autowired
    private val housingLoanRepository: HousingLoanRepository? = null
    fun findAllHousingLoan(): Flux<HousingLoanEvent?> {
        return housingLoanRepository?.findAll()!!
    }

    fun findHousingLoanById(id: String?): Mono<HousingLoanEvent?> {
        return housingLoanRepository?.findById(id!!)!!
    }

    fun findHousingLoanByMobileNo(mobileNo: Long?): Mono<HousingLoanEvent?> {
        return housingLoanRepository?.findHousingLoanByCustomerMobileNo(mobileNo!!)!!
    }

    fun deleteHousingLoanById(id: String?): Mono<Void> {
        return housingLoanRepository?.deleteById(id!!)!!
    }
}
