package com.shana.authentication.di

import android.content.Context
import com.shana.authentication.remoteDataSource.AuthApi
import com.shana.authentication.remoteDataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideAuthApi(
        @ApplicationContext context: Context,
        remoteDataSource: RemoteDataSource
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java)
    }
}