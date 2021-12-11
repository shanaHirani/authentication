package com.shana.authentication.data.repository

import com.shana.authentication.base.BaseRepository
import com.shana.authentication.data.UserPreferences
import com.shana.authentication.data.remoteDataSource.apis.UserApi
import com.shana.authentication.data.remoteDataSource.remoteData.ProfessorListResponse
import com.shana.authentication.data.remoteDataSource.remoteData.asDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: UserApi,
    private val userPreferences: UserPreferences
) : BaseRepository(api,userPreferences) {

    suspend fun getProfessor() = safeApiCall {
        api.getProfessorList().asDomain()
    }
}