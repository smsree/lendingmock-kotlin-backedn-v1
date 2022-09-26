package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.BusinessLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.service.BusinessLoanEventControllerService
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

@WebFluxTest(controllers = [BusinessLoanEventController::class])
@AutoConfigureWebTestClient
class BusinessLoanControllerUnitTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    var businessLoanServiceMock: BusinessLoanEventControllerService? = null
    @Test
    fun welcomeTestMethod() {
        webTestClient
            ?.get()
            ?.uri("/v1/businessLoan/")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(String::class.java)
            ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                val responseBody = stringEntityExchangeResult.responseBody
                Assertions.assertEquals(responseBody, "Welcome to the axis bank business loan section")
            }
    }

    @Test
    fun getAllLoanTest() {
        val businessLoan = BusinessLoanEvent("id", "farming", 2L, "mitra loan", 1000, 2.1, "applied",LoanEventType.NEW)
        Mockito.`when`(businessLoanServiceMock!!.getAllBusinessLoanAvailable())
            .thenReturn(Flux.just(businessLoan))
        val responseBody: Flux<BusinessLoanEvent> = webTestClient
            ?.get()
            ?.uri("/v1/businessLoan/allBuisnessLoan")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(BusinessLoanEvent::class.java)
            ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }

    @Test
    fun getLoanByCustomerMobileNo() {
        val businessLoan = BusinessLoanEvent("id", "farming", 2L, "mitra loan", 1000, 2.1, "applied",LoanEventType.NEW)
        Mockito.`when`(businessLoanServiceMock!!.findBusinessLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
            .thenReturn(Mono.just(businessLoan))
        webTestClient
            ?.get()
            ?.uri("/v1/businessLoan/customerMobileNo/2")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(BusinessLoanEvent::class.java)
            ?.consumeWith { educationalLoanEntityExchangeResult ->
                val responseBody = educationalLoanEntityExchangeResult.responseBody
                assert(responseBody!!.getCustomerMobileNo()!! >= 2)
            }
    }

    @Test
    fun deleteMethodTest() {
        Mockito.`when`(businessLoanServiceMock!!.deleteBusinessLoanByIdService(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.empty())
        webTestClient
            ?.delete()
            ?.uri("/v1/businessLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }
}
