package com.shana.authentication.data.repository

import androidx.lifecycle.asLiveData
import com.shana.authentication.base.BaseRepository
import com.shana.authentication.data.UserPreferences
import com.shana.authentication.data.remoteDataSource.apis.AuthApi
import com.shana.authentication.data.remoteDataSource.remoteData.LoginRequestInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val userPreferences: UserPreferences
    ): BaseRepository(api,userPreferences) {

        val accessToken = userPreferences.accessToken.asLiveData()

        suspend fun login(userName:String,password:String)=  safeApiCall {
                api.logIn(LoginRequestInfo(userName,password))
            }

        suspend fun saveAuthToken(token:String){
            userPreferences.saveAccessTokens(token, token)
        }
}