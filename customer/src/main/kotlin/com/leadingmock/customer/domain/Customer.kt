package com.leadingmock.customer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive


@Document
class Customer {
    @Id
    private var customerId:String? = null
    @NotBlank(message = "The Customer name cannot be blank")
    private var name:String? = null
    @NotNull()
    @Positive(message = "The Phone Number cannot be blank and it should be a positive value")
    @Indexed(unique = true)
    private var phoneNumber:Long? = null
    @NotBlank(message = "The customer Email cannot be blank")
    private var email:String? = null
    @NotBlank(message = "The Customer's date of Birth cannot be blank")
    private var dateOfBirth:String? = null
    @NotNull
    @Positive(message = "adhar number must  be a positive value")
    private var adhar:Long? = null
    @NotBlank(message = "the customer pan number cannot be blank")
    private var panNumber:String? = null

    fun getCustomerId():String?{
        return this.customerId
    }
    fun getName():String?{
        return this.name
    }
    fun getPhoneNumber():Long?{
        return this.phoneNumber
    }
    fun getEmail():String?{
        return this.email
    }
    fun getDateOfBirth():String?{
        return this.dateOfBirth
    }
    fun getAdhar():Long?{
        return this.adhar
    }
    fun getPanNumber():String?{
        return this.panNumber
    }
    fun setCustomerId(customer:String?){
        this.customerId=customerId
    }
    fun setName(name:String?){
        this.name=name
    }
    fun setPhoneNumber(phoneNumber:Long?){
        this.phoneNumber=phoneNumber
    }
    fun setEmail(email:String?){
        this.email=email
    }
    fun setDateOfBirth(dateOfBirth:String?){
        this.dateOfBirth=dateOfBirth
    }
    fun setAdhar(adharNumber:Long?){
        this.adhar=adharNumber
    }
    fun setPanNumber(panNumber: String?){
        this.panNumber=panNumber
    }

    override fun toString(): String {
        return "Customer(customerId=$customerId, name=$name, phoneNumber=$phoneNumber, email=$email, dateOfBirth=$dateOfBirth, adhar=$adhar, panNumber=$panNumber)"
    }

    constructor(){}
    constructor(
        customerId: String?,
        name: String?,
        phoneNumber: Long?,
        email: String?,
        dateOfBirth: String?,
        adharNumber: Long?,
        panNumber: String?
    ) {
        this.customerId = customerId
        this.name = name
        this.phoneNumber = phoneNumber
        this.email = email
        this.dateOfBirth = dateOfBirth
        this.adhar = adharNumber
        this.panNumber = panNumber
    }

}