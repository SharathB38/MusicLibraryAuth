package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class UserCheck(val name:String,val email:String,val password:String)