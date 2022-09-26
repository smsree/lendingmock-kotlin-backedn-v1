package com.leadingmock.offer.controller

import com.leadingmock.offer.domain.Offer
import com.leadingmock.offer.service.OfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/offer")
@CrossOrigin
class OfferController {
    @Autowired
    private var offerService: OfferService?=null;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addOffers(@RequestBody offer: Offer?): Mono<Offer?>?{
        return offerService?.addOffer(offer);
    }
    @GetMapping("/{phoneNumber}")
    fun getOfferForCustomer(@PathVariable("phoneNumber") phoneNumber:Long?): Flux<Offer?>?{
        return offerService?.getAllOffer(phoneNumber);
    }
    @DeleteMapping("/{offerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOffer(@PathVariable("offerId") offerId:String?):Mono<Void?>?{
        return offerService?.deleteCustomer(offerId);
    }
}