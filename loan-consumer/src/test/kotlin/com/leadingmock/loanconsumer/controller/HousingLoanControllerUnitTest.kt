package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.HousingLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.service.HousingLoanControllerService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@WebFluxTest(controllers = [HousingLoanEventController::class])
@AutoConfigureWebTestClient
class HousingLoanControllerUnitTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    private val housingLoanService: HousingLoanControllerService? = null
    @Test
    fun welcomeMethodTest() {
        webTestClient
            ?.get()
            ?.uri("/v1/housingLoan/")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(String::class.java)
            ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                val responseBody = stringEntityExchangeResult.responseBody
                Assertions.assertEquals(responseBody, "Welcome to axis bank housing loan section")
            }
    }
    @Test
    fun getAllLoanTest() {
        val housingLoan = HousingLoanEvent("id", 2L, "address", "housing loan", 12, 2.3, "applied",LoanEventType.NEW)
        Mockito.`when`(housingLoanService!!.findAllHousingLoan())
            .thenReturn(Flux.just(housingLoan))
        val responseBody: Flux<HousingLoanEvent> = webTestClient
            ?.get()
            ?.uri("/v1/housingLoan/allHousingLoan")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(HousingLoanEvent::class.java)
            ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }
    @Test
    fun getHousingLoanByCustomerMobileNo() {
        val housingLoan = HousingLoanEvent("id", 2L, "address", "housing loan", 12, 2.3, "applied",LoanEventType.NEW)
        Mockito.`when`(housingLoanService!!.findHousingLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
            .thenReturn(Mono.just(housingLoan))
        webTestClient
            ?.get()
            ?.uri("/v1/housingLoan/customerMobileNo/2")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(HousingLoanEvent::class.java)
            ?.consumeWith { educationalLoanEntityExchangeResult ->
                val responseBody = educationalLoanEntityExchangeResult.responseBody
                assert(responseBody!!.getCustomerMobileNo()!! >= 2)
            }
    }
    @Test
    fun deleteMethodTest() {
        Mockito.`when`(housingLoanService!!.deleteHousingLoanById(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.empty())
        webTestClient
            ?.delete()
            ?.uri("/v1/housingLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }
}