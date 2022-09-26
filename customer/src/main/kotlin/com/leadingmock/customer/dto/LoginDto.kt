package com.leadingmock.customer.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class LoginDto {
    @NotNull
    private var phoneNumber:Long? = null
    @NotBlank(message = "date of birth is needed ")
    private var dateOfBirth:String? = null

    constructor(phoneNumber: Long?, birthDate: String?) {
        this.phoneNumber = phoneNumber
        this.dateOfBirth = birthDate
    }

    fun getPhoneNumber():Long?{
        return this.phoneNumber
    }
    fun getDateOfBirth():String?{
        return  this.dateOfBirth
    }
    fun setPhoneNumber(phoneNumber: Long?){
        this.phoneNumber=phoneNumber
    }
    fun setDateOfBirth(birthDate: String?){
        this.dateOfBirth=birthDate
    }

    override fun toString(): String {
        return "LoginDto(phoneNumber=$phoneNumber, dateOfBirth=$dateOfBirth)"
    }

}