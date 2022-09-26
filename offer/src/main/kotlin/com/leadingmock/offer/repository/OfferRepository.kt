package com.leadingmock.offer.repository

import com.leadingmock.offer.domain.Offer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface OfferRepository:ReactiveMongoRepository<Offer,String?> {
     fun findOfferByPhoneNumber(phoneNumber: Long?): Flux<Offer?>?
}