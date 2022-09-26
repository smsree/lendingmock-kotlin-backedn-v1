package com.leadingmock.customer.controller

import com.leadingmock.customer.domain.Admin
import com.leadingmock.customer.service.AdminService
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(controllers = [AdminController::class])
@AutoConfigureWebTestClient
class AdminControllerTest {
    @Autowired
    private val webTestClient: WebTestClient? = null
    @MockBean
    private val adminService:AdminService?=null
    @Test
    fun registerAdmin(){
        var ad = Admin("id","name","password",false);
        Mockito.`when`(adminService?.registerNewAdmin(ArgumentMatchers.isA(Admin::class.java)))
            .thenReturn(Mono.just(ad))
        webTestClient
            ?.post()
            ?.uri("/v1/admin/register")
            ?.bodyValue(ad)
            ?.exchange()
            ?.expectStatus()
            ?.isCreated
            ?.expectBody(Admin::class.java)
            ?.consumeWith{cust->
                var k = cust.responseBody
                assert(k?.getAdminName() == "name")
            }
    }

    @Test
    fun adminLoginTest(){
        var ad = Admin("id","name","password",false);
        Mockito.`when`(adminService?.validateAdminLogin(ArgumentMatchers.isA(Admin::class.java)))
            .thenReturn(Mono.just(ad))
        webTestClient
            ?.post()
            ?.uri("/v1/admin/login")
            ?.bodyValue(ad)
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
            ?.expectBody(Admin::class.java)
            ?.consumeWith{a->
                var l=a.responseBody
                assert(l?.getAdminName() == "name")
            }
    }
    @Test
    fun approveAdminTest(){
        var ad = Admin("id","name","password",false);
        Mockito.`when`(adminService?.approveAdminService(ArgumentMatchers.isA(String::class.java)))
            .thenReturn(Mono.just(ad))
        webTestClient
            ?.put()
            ?.uri("/v1/admin/approveAdmin/name")
            ?.exchange()
            ?.expectStatus()
            ?.is2xxSuccessful
    }

}