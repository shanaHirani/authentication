package com.shana.authentication.data.remoteDataSource.apis
import com.shana.authentication.data.remoteDataSource.remoteData.ProfessorListResponse
import retrofit2.http.POST

interface UserApi {
    @POST("Professor/ProfessorList")
    suspend fun getProfessorList(): ProfessorListResponse
}