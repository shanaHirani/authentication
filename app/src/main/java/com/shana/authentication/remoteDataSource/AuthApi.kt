package com.shana.authentication.remoteDataSource

import com.shana.authentication.remoteDataSource.remoteData.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("Authentication/Login")
    suspend fun logIn(
        @Field("Username")userName:String,
        @Field("Password")passWord:String
    ):LoginResponse
}