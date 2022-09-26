package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.EducationalLoanEvent
import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.service.EducationalLoanControllerEventService
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

@WebFluxTest(controllers = [EducationalLoanEventController::class])
@AutoConfigureWebTestClient
class EducationalLoanControllerUnitTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    var educationalLoanServiceMock: EducationalLoanControllerEventService? = null

    @Test
    fun welcomeTestMethod() {
        webTestClient
            ?.get()
            ?.uri("/v1/educationalLoan/")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(String::class.java)
            ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                val responseBody = stringEntityExchangeResult.responseBody
                Assertions.assertEquals(responseBody, "Welcome to the axis bank educational loan section")
            }
    }
    @Test
    fun getAllLoanTest() {
        val educationalLoan = EducationalLoanEvent("id", 2L, "2L", "abc", 10, 2.4, "applied",LoanEventType.NEW)
        Mockito.`when`(educationalLoanServiceMock!!.findAllEducationalLoan())
            .thenReturn(Flux.just(educationalLoan))
        val responseBody: Flux<EducationalLoanEvent> = webTestClient
            ?.get()
            ?.uri("/v1/educationalLoan/allEducationalLoan")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(EducationalLoanEvent::class.java)
            ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }
    @Test
    fun getEducationalLoanByCustomerMobileNo() {
        val educationalLoan = EducationalLoanEvent("id", 2L, "2L", "abc", 10, 2.4, "applied",LoanEventType.NEW)
        Mockito.`when`(educationalLoanServiceMock!!.findEducationalLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
            .thenReturn(Mono.just(educationalLoan))
        webTestClient
            ?.get()
            ?.uri("/v1/educationalLoan/customerMobileNo/2")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(EducationalLoanEvent::class.java)
            ?.consumeWith { educationalLoanEntityExchangeResult ->
                val responseBody = educationalLoanEntityExchangeResult.responseBody
                assert(responseBody!!.getCustomerMobileNo()!! >= 2)
            }
    }
    @Test
    fun getLoanById() {
        val educationalLoan = EducationalLoanEvent("id", 2L, "2L", "abc", 10, 2.4, "applied",LoanEventType.NEW)
        Mockito.`when`(educationalLoanServiceMock!!.findEducationalLoanById(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.just(educationalLoan))
        webTestClient
            ?.get()
            ?.uri("/v1/educationalLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(EducationalLoanEvent::class.java)
            ?.consumeWith { loanEntityExchangeResult ->
                val responseBody = loanEntityExchangeResult.responseBody
                assert(responseBody!!.getEducationalLoanId().equals("id"))
            }
    }
    @Test
    fun deleteMethodTest() {
        Mockito.`when`(educationalLoanServiceMock!!.deleteEducationalLoanById(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.empty())
        webTestClient
            ?.delete()
            ?.uri("/v1/educationalLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }

}