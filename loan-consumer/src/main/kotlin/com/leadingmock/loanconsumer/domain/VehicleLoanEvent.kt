package com.leadingmock.loanconsumer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
@Document
class VehicleLoanEvent {
    @Id
    private var vehicleLoanId: String? = null
    @NotBlank(message = "vehicle name is need to proceed for the vehicle loan")
    private var vehicleName: String? = null
    @NotNull
    @Positive(message = "customer mobile number should be a positive value")
    private var customerMobileNo: Long? = null
    @NotBlank(message = "loan name cannot be blank value")
    private var loanName: String? = null
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private var loanamount: Int? = null
    @Min(value = 0L, message = "rating of interest should not be negative")
    private var rateOfInterest: Double? = null
    private var status: String? = null
    private var loanEventType:LoanEventType? = null
    fun getVehicleLoanId():String?{
        return this.vehicleLoanId;
    }
    fun getVehicleName():String?{
        return this.vehicleName
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
    fun setLoanamount(loanamount: Int?){
        this.loanamount=loanamount
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
    fun setVehicleLoanId(vehicleLoanId:String?){
        this.vehicleLoanId=vehicleLoanId;
    }
    fun setVehicleName(vehicleName:String?){
        this.vehicleName=vehicleName;
    }
    fun setCustomerMobileNo(customerMobileNo:Long?){
        this.customerMobileNo=customerMobileNo
    }
    fun setLoanName(loanName:String?){
        this.loanName=loanName;
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
    constructor(vehicleLoanId: String?,vehicleName: String?,customerMobileNo: Long?,loanName: String?,loanamount: Int?,rateOfInterest: Double?,status: String?,loanEventType: LoanEventType?){
        this.vehicleLoanId=vehicleLoanId
        this.vehicleName=vehicleName;
        this.customerMobileNo=customerMobileNo
        this.loanName=loanName
        this.loanamount=loanamount
        this.rateOfInterest=rateOfInterest
        this.status=status
        this.loanEventType=loanEventType
    }
}