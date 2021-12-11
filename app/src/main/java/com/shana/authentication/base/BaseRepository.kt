package com.shana.authentication.base

import com.shana.authentication.data.UserPreferences
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.data.remoteDataSource.apis.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

abstract class BaseRepository(
    private val api: BaseApi,
    private val userPreferences: UserPreferences,
) {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }

            }
        }
    }

    suspend fun logOut() {
        safeApiCall {
            api.logOut()
        }
        userPreferences.clear()
    }

}