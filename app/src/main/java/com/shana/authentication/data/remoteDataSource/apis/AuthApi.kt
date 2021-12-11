package com.shana.authentication.data.remoteDataSource.apis

import com.shana.authentication.base.BaseApi
import com.shana.authentication.data.remoteDataSource.remoteData.LoginRequestInfo
import com.shana.authentication.data.remoteDataSource.remoteData.LoginResponse
import retrofit2.http.*


interface AuthApi :BaseApi{
    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    @POST("Authentication/Login")
    suspend fun logIn(
        @Body LoginRequestInfo: LoginRequestInfo
    ):LoginResponse
}