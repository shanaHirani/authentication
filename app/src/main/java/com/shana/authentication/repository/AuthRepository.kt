package com.shana.authentication.repository

import com.shana.authentication.remoteDataSource.AuthApi

class AuthRepository(
    private val api:AuthApi
    ):BaseRepository() {
        suspend fun loginI(userName:String,password:String) = safeApiCall {
            api.logIn(userName,password)
        }
}