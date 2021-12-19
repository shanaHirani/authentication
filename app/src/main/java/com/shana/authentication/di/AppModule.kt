package com.shana.authentication.di


import com.shana.authentication.data.UserPreferences
import com.shana.authentication.data.remoteDataSource.apis.AuthApi
import com.shana.authentication.data.remoteDataSource.RemoteDataSource
import com.shana.authentication.data.remoteDataSource.apis.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        userPreferences: UserPreferences,
        remoteDataSource: RemoteDataSource
    ): UserApi {
        //take care getting token is Asynchronous
        val token= runBlocking {userPreferences.refreshToken.first()}
        return remoteDataSource.buildApi(UserApi::class.java, token)
    }
}