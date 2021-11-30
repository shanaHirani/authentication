package com.shana.authentication.data.remoteDataSource.remoteData

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