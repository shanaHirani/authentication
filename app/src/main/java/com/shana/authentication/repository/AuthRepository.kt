package com.shana.authentication.repository

import com.shana.authentication.base.BaseRepository
import com.shana.authentication.remoteDataSource.AuthApi
import com.shana.authentication.remoteDataSource.Resource
import com.shana.authentication.remoteDataSource.remoteData.LoginRequestInfo
import com.shana.authentication.remoteDataSource.remoteData.LoginResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api:AuthApi
    ): BaseRepository() {
        suspend fun login(userName:String,password:String)=  safeApiCall {
                api.logIn(LoginRequestInfo(userName,password))
            }
}