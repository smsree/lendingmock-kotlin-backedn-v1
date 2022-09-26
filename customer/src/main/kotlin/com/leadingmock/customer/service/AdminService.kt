package com.leadingmock.customer.service

import com.leadingmock.customer.domain.Admin
import com.leadingmock.customer.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AdminService {
    @Autowired
    private val adminRepository:AdminRepository?=null
    fun registerNewAdmin(admin: Admin?): Mono<Admin?>? {
        return adminRepository!!.save(admin!!)
    }

    fun validateAdminLogin(admin: Admin?): Mono<Admin?>? {
        return adminRepository!!.findAdminByAdminName(admin?.getAdminName())
            ?.filter{ad->ad?.getAdminPassword() == admin?.getAdminPassword()}
    }

    fun approveAdminService(name: String?): Mono<Admin?>? {
        return adminRepository!!.findAdminByAdminName(name)
            ?.flatMap { temp->
                temp?.setIsApproved(true)
                adminRepository.save(temp!!)
            }
    }
}