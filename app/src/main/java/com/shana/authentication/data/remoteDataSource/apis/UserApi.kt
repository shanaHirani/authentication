package com.shana.authentication.data.remoteDataSource.apis
import com.shana.authentication.base.BaseApi
import com.shana.authentication.data.remoteDataSource.remoteData.ProfessorListResponse
import retrofit2.http.POST

interface UserApi:BaseApi {
    @POST("Professor/ProfessorList")
    suspend fun getProfessorList(): ProfessorListResponse
}