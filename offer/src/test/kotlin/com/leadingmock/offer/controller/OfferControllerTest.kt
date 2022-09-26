package com.leadingmock.offer.controller

import com.leadingmock.offer.domain.Offer
import com.leadingmock.offer.service.OfferService
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

@WebFluxTest(controllers = [OfferController::class])
@AutoConfigureWebTestClient
class OfferControllerTest {
    @Autowired
    private var webTestClient:WebTestClient?=null

    @MockBean
    private var offerService:OfferService?=null

    @Test
    fun postOffer(){
        var offer = Offer("id",123,"sre",1.2,100L,1.0)
        Mockito.`when`(offerService?.addOffer(ArgumentMatchers.isA(Offer::class.java)))
            ?.thenReturn(Mono.just(offer))
        webTestClient
            ?.post()
            ?.uri("/v1/offer/")
            ?.bodyValue(offer)
            ?.exchange()
            ?.expectStatus()
            ?.isCreated
            ?.expectBody(Offer::class.java)
            ?.consumeWith { k->
                var of1 = k.responseBody
                assert(of1?.getLoanName() == "sre")
            }
    }

    @Test
    fun getAllOfferTest(){
        var offer = Offer("id",123,"sre",1.2,100L,1.0)
        Mockito.`when`(offerService?.getAllOffer(ArgumentMatchers.isA(Long::class.java)))
            ?.thenReturn(Flux.just(offer))
        var res:Flux<Offer?>?= webTestClient
            ?.get()
            ?.uri("/v1/offer/123")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.returnResult(Offer::class.java)
            ?.getResponseBody()!!
        StepVerifier.create(res!!).expectNextCount(1).verifyComplete()
    }

    @Test
    fun deleteOffer(){
        Mockito.`when`(offerService?.deleteCustomer(ArgumentMatchers.isA(String::class.java)))
            ?.thenReturn(Mono.empty())
        webTestClient
            ?.delete()
            ?.uri("/v1/offer/id")
            ?.exchange()
            ?.expectStatus()
            ?.isNoContent
    }
}