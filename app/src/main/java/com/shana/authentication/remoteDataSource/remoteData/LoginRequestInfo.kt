package com.shana.authentication.remoteDataSource.remoteData

import com.google.gson.annotations.SerializedName

data class LoginRequestInfo (@SerializedName("Username") val userName: String,
                             @SerializedName("Password") val passWord: String)


