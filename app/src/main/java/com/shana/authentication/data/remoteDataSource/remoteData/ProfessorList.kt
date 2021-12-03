package com.shana.authentication.data.remoteDataSource.remoteData

import com.shana.authentication.data.domainData.Professor

class ProfessorListResponse : ArrayList<ProfessorResponseItem>()

data class ProfessorResponseItem(
    val accessFailedCount: Int,
    val concurrencyStamp: String,
    val email: Any,
    val emailConfirmed: Boolean,
    val family: String,
    val id: String,
    val imgSrc: Any,
    val lockoutEnabled: Boolean,
    val lockoutEnd: Any,
    val meliCode: String,
    val name: String,
    val normalizedEmail: Any,
    val normalizedUserName: String,
    val passwordHash: String,
    val phoneNumber: String,
    val phoneNumberConfirmed: Boolean,
    val securityStamp: String,
    val twoFactorEnabled: Boolean,
    val userName: String
)

fun ProfessorListResponse.asDomain(): List<Professor> {
    return this.map{
        Professor(
            id=it.id,
            userName = it.userName,
            family = it.family
        )
    }
}