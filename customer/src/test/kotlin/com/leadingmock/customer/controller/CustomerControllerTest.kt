package com.leadingmock.customer.controller

import com.leadingmock.customer.domain.Customer
import com.leadingmock.customer.dto.LoginDto
import com.leadingmock.customer.repository.CustomerRepository
import com.leadingmock.customer.service.CustomerService
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@WebFluxTest(controllers = [CustomerController::class])
@AutoConfigureWebTestClient
class CustomerControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null
    @MockBean
    private val customerServiceMock:CustomerService?=null

    @MockBean
    private val customerRepository:CustomerRepository? = null

    @Test
    fun registerControllerTest(){
       var customer = Customer("id","name",123L,"email","don",123L,"pan")
        customer.setCustomerId("id")
        Mockito.`when`(customerServiceMock?.registerNewCustomer(ArgumentMatchers.isA(Customer::class.java)))
            .thenReturn(Mono.just(customer))
        webTestClient
            ?.post()
            ?.uri("/v1/customer/register")
            ?.bodyValue(customer!!)
            ?.exchange()
            ?.expectStatus()
            ?.isCreated
            ?.expectBody(Customer::class.java)
            ?.consumeWith{customer1->
                var k = customer1.responseBody
                println(k)
                assert( k?.getName() == "name")
            }

    }
    @Test
    fun validateLoginTest(){
        var customer = Customer("id","name",123L,"email","don",123L,"pan")
        var login = LoginDto(123,"don")
        Mockito.`when`(customerServiceMock?.validateLogin(login ))
            .thenReturn(Mono.just(customer))
        webTestClient
            ?.post()
            ?.uri("/v1/customer/login")
            ?.bodyValue(login)
            ?.exchange()
            ?.expectStatus()
            ?.isOk
    }
    @Test
    fun getAllcustomer(){
        var customer = Customer("id","name",123L,"email","don",123L,"pan")
        Mockito.`when`(customerServiceMock!!.findAllcustomer())
            .thenReturn(Flux.just(customer))
        val responseBody = webTestClient
            ?.get()
            ?.uri("/v1/customer")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(Customer::class.java)
            ?.getResponseBody()!!
        StepVerifier.create(responseBody).expectNextCount(1).verifyComplete()
    }
    @Test
    fun deleteCustomerController(){
        Mockito.`when`(customerServiceMock?.deleteByCustomerId(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.empty());
        webTestClient
            ?.delete()
            ?.uri("/v1/customer/delete/k")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }
}