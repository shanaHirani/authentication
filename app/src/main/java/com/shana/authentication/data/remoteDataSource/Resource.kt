package com.shana.authentication.data.remoteDataSource

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetWorkError: Boolean,
        val errorCode: Int?,
        val errorResponseBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
    object Start : Resource<Nothing>()
}