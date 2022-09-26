package com.leadingmock.customer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "Admin")
class Admin {
    @Id
    private var adminId:String? = null

    private var adminName:String? = null
    private var adminPassword:String? = null
    private var isApproved:Boolean? = false;

    constructor(){}

    constructor(adminId:String?,adminName:String?,adminPassword:String?,isApproved:Boolean?){
        this.adminId = adminId
        this.adminName = adminName
        this.adminPassword = adminPassword
        this.isApproved = isApproved
    }
    fun getAdminId():String?{
        return  this.adminId
    }
    fun getAdminName():String?{
        return this.adminName
    }
    fun getAdminPassword():String?{
        return this.adminPassword
    }
    fun getIsApproved():Boolean?{
        return this.isApproved
    }
    fun setAdminId(adminId:String?){
        this.adminId = adminId
    }
    fun setAdminName(adminName:String?){
        this.adminName = adminName
    }
    fun setAdminPassword(adminPassword:String? ){

        this.adminPassword = adminPassword
    }
    fun setIsApproved(isApproved:Boolean? ){
        this.isApproved = isApproved
    }
}