package com.leadingmock.offer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Offer {
    @Id
    private var offerId:String? = null
    private var phoneNumber:Long? = null
    private var loanName:String? = null
    private var rateOfInterest:Double?=null
    private var loanAmount:Long? = null
    private var timePeriod:Double? = null
    fun getOfferId():String?{
        return this.offerId
    }
    fun getPhoneNumber():Long?{
        return this.phoneNumber
    }
    fun getLoanName():String?{
        return this.loanName
    }
    fun getRateOfInterest():Double?{
        return this.rateOfInterest
    }
    fun getLoanAmount():Long?{
        return this.loanAmount
    }
    fun getTimePeriod():Double?{
        return this.timePeriod
    }
    fun setOfferId(offerId:String?){
        this.offerId=offerId
    }
    fun setPhoneNumber(phoneNumber:Long?){
        this.phoneNumber=phoneNumber
    }
    fun setLoanName(loanName:String?){
        this.loanName=loanName
    }
    fun setRateOfInterest(rateOfInterest:Double?){
        this.rateOfInterest=rateOfInterest
    }
    fun setLoanAmount(loanAmount:Long?){
        this.loanAmount=loanAmount
    }
    fun setTimePeriod(timePeriod:Double?){
        this.timePeriod=timePeriod
    }
    constructor(
        offerId: String?,
        phoneNumber: Long?,
        loanName: String?,
        rateOfInterest: Double?,
        loanAmount: Long?,
        timePeriod: Double?
    ) {
        this.offerId = offerId
        this.phoneNumber = phoneNumber
        this.loanName = loanName
        this.rateOfInterest = rateOfInterest
        this.loanAmount = loanAmount
        this.timePeriod = timePeriod
    }

}