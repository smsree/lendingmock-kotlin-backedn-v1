package com.leadingmock.customer.repository

import com.leadingmock.customer.domain.Admin
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface AdminRepository : ReactiveMongoRepository<Admin?, String?> {
     fun findAdminByAdminName(adminName: String?): Mono<Admin?>?
}