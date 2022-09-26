package com.leadingmock.offer.service

import com.leadingmock.offer.domain.Offer
import com.leadingmock.offer.repository.OfferRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OfferService {
    @Autowired
    private val offerRepository:OfferRepository?=null
    fun addOffer(offer: Offer?): Mono<Offer?>? {
        return offerRepository?.save(offer!!);
    }

    fun getAllOffer(phoneNumber: Long?): Flux<Offer?>? {
        return offerRepository?.findOfferByPhoneNumber(phoneNumber);
    }

    fun deleteCustomer(offerId: String?): Mono<Void?>? {
        return offerRepository?.deleteById(offerId!!)
    }
}