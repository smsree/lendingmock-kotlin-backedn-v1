package com.leadingmock.loanconsumer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
@Document
class EducationalLoanEvent {
    @Id
    private var educationalLoanId: String? = null
    @NotNull
    @Positive(message = "customer mobile number should be a positive value")
    private var customerMobileNo: Long? = null
    @NotBlank(message = "educational instituation name is required in order to proceed")
    private var collegeName: String? = null
    @NotBlank(message = "loan name cannot be blank value")
    private var loanName: String? = null
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private var loanamount: Int? = null
    @Min(value = 0L, message = "rating of interest should not be negative")
    private var rateOfInterest: Double? = null
    private var status: String? = null
    private var loanEventType: LoanEventType? = null
    fun getEducationalLoanId():String?{
        return this.educationalLoanId;
    }
    fun getCollegeName():String?{
        return this.collegeName;
    }
    fun getCustomerMobileNo():Long?{
        return this.customerMobileNo;
    }
    fun getLoanName():String?{
        return this.loanName;
    }
    fun getLoanamount():Int?{
        return this.loanamount;
    }
    fun getRateOfInterest():Double?{
        return this.rateOfInterest;
    }
    fun getStatus():String?{
        return this.status;
    }
    fun getLoanEventType():LoanEventType?{
        return this.loanEventType;
    }
    fun setEducationalLoanId(educationalLoanId:String?){
        this.educationalLoanId=educationalLoanId;
    }
    fun setCollegeName(collegeName:String?){
        this.collegeName=collegeName
    }
    fun setCustomerMobileNo(customerMobileNo:Long?){
        this.customerMobileNo=customerMobileNo
    }
    fun setLoanName(loanName:String?){
        this.loanName=loanName;
    }
    fun setLoanamount(loanamount: Int?){
        this.loanamount=loanamount
    }
    fun setRateOfInterest(rateOfInterest:Double?){
        this.rateOfInterest=rateOfInterest;
    }
    fun setStatus(status:String?){
        this.status=status;
    }
    fun setLoanEventType(loanEventType: LoanEventType?){
        this.loanEventType = loanEventType;
    }
    constructor(){}
    constructor(educationalLoanId: String?,customerMobileNo: Long?,collegeName: String?,loanName: String?,loanamount: Int?,rateOfInterest: Double?,status: String?,loanEventType: LoanEventType?){
        this.educationalLoanId=educationalLoanId
        this.customerMobileNo=customerMobileNo
        this.collegeName=collegeName
        this.loanName = loanName
        this.loanamount = loanamount
        this.rateOfInterest = rateOfInterest
        this.status = status
        this.loanEventType = loanEventType
    }
}