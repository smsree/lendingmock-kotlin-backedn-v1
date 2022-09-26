package com.leadingmock.loanconsumer.controller

import com.leadingmock.loanconsumer.domain.LoanEventType
import com.leadingmock.loanconsumer.domain.VehicleLoanEvent
import com.leadingmock.loanconsumer.service.VehicleLoanEventControllerService
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

@WebFluxTest(controllers = [VehicleLoanEventController::class])
@AutoConfigureWebTestClient
class VehicleLoanControllerUnitTest {
    @Autowired
    private val webTestClient: WebTestClient? = null

    @MockBean
    var vehicleLoanServiceMock: VehicleLoanEventControllerService? = null
    @Test
    fun welcomeTestController() {
        webTestClient
            ?.get()
            ?.uri("/v1/vehicleLoan/")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(String::class.java)
            ?.consumeWith { stringEntityExchangeResult: EntityExchangeResult<String?> ->
                val responseBody = stringEntityExchangeResult.responseBody
                Assertions.assertEquals(responseBody, "Welcome to axis bank vehicle loan section")
            }
    }
    @Test
    fun getAllLoanTest() {
        val vehicle = VehicleLoanEvent("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied",LoanEventType.NEW)
        Mockito.`when`(vehicleLoanServiceMock!!.getAllVehicleLoan())
            .thenReturn(Flux.just(vehicle))
        val responseBody = webTestClient
            ?.get()
            ?.uri("/v1/vehicleLoan/allVehicleLoan")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(VehicleLoanEvent::class.java)
            ?.responseBody
        StepVerifier.create(responseBody!!).expectNextCount(1).verifyComplete()
    }
    @Test
    fun getEducationalLoanByCustomerMobileNo() {
        val vehicle = VehicleLoanEvent("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied",LoanEventType.NEW)
        Mockito.`when`(vehicleLoanServiceMock!!.getVehicleLoanByMobileNo(ArgumentMatchers.isA(Long::class.java)))
            .thenReturn(Mono.just(vehicle))
        webTestClient
            ?.get()
            ?.uri("/v1/vehicleLoan/customerMobileNo/2")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(VehicleLoanEvent::class.java)
            ?.consumeWith { vehicleLoanEntityExchangeResult ->
                val responseBody = vehicleLoanEntityExchangeResult.responseBody
                assert(responseBody!!.getCustomerMobileNo()!! >= 2)
            }
    }
    @Test
    fun getLoanById() {
        val vehicle = VehicleLoanEvent("id", "scooter", 2L, "vehicle loan", 10000, 2.3, "Applied",LoanEventType.NEW)
        Mockito.`when`(vehicleLoanServiceMock!!.getVehicleLoanById(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.just(vehicle))
        webTestClient
            ?.get()
            ?.uri("/v1/vehicleLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(VehicleLoanEvent::class.java)
            ?.consumeWith { loanEntityExchangeResult ->
                val responseBody = loanEntityExchangeResult.responseBody
                assert(responseBody!!.getVehicleLoanId().equals("id"))
            }
    }
    @Test
    fun deleteMethodTest() {
        Mockito.`when`(vehicleLoanServiceMock!!.deleteVehicleLoanById(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.empty())
        webTestClient
            ?.delete()
            ?.uri("/v1/vehicleLoan/id")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }
}