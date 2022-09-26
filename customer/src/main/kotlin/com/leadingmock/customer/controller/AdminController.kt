package com.leadingmock.customer.controller

import com.leadingmock.customer.domain.Admin
import com.leadingmock.customer.repository.AdminRepository
import com.leadingmock.customer.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/v1/admin")
@CrossOrigin
class AdminController {
    @Autowired
    private val adminService:AdminService?=null
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewAdmin(@RequestBody admin:Admin? ): Mono<Admin?>?{
        return adminService?.registerNewAdmin(admin);
    }
    @PostMapping("/login")
    fun adminLogin(@RequestBody admin:Admin?):Mono<ResponseEntity<Admin?>?>?{
        return adminService?.validateAdminLogin(admin)
            ?.map {ad->ResponseEntity.ok().body(ad)  }
            ?.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
    }
    @PutMapping("/approveAdmin/{adminName}")
    fun approveAdmin(@PathVariable("adminName") name:String?):Mono<Admin?>?{
        return adminService?.approveAdminService(name);
    }

}