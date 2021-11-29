package com.shana.authentication.remoteDataSource

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("Authentication/Login")
    fun logIn(
        @Field("Username")userName:String,
        @Field("Password")passWord:String
    ):Any
}