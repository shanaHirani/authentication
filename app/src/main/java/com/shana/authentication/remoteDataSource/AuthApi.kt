package com.shana.authentication.remoteDataSource

import com.shana.authentication.remoteDataSource.remoteData.LoginRequestInfo
import com.shana.authentication.remoteDataSource.remoteData.LoginResponse
import retrofit2.http.*

interface AuthApi {
    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    @POST("Authentication/Login")
    suspend fun logIn(
        @Body LoginRequestInfo: LoginRequestInfo
    ):LoginResponse
}