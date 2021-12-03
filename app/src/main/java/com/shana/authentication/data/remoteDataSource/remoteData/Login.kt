package com.shana.authentication.data.remoteDataSource.remoteData

import com.google.gson.annotations.SerializedName

data class LoginRequestInfo (@SerializedName("Username") val userName: String,
                             @SerializedName("Password") val passWord: String)


data class LoginResponse(
    val exception: String,
    val family: String,
    val img: Any,
    val name: String,
    val role: List<String>,
    val token: String,
    val tokenExpire: String,
    val user: String
)