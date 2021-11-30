package com.shana.authentication.data.repository

import com.shana.authentication.base.BaseRepository
import com.shana.authentication.data.remoteDataSource.AuthApi
import com.shana.authentication.data.remoteDataSource.remoteData.LoginRequestInfo
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api:AuthApi
    ): BaseRepository() {
        suspend fun login(userName:String,password:String)=  safeApiCall {
                api.logIn(LoginRequestInfo(userName,password))
            }
}