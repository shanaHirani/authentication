package com.shana.authentication.data.remoteDataSource


import com.shana.authentication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {
    companion object {
        private const val BASE_URL = "https://lms.jde.ir:86/api/"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken:String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor{chain ->  
                    chain.proceed(chain.request().newBuilder().also{
                        it.addHeader("Authorization","Bearer $authToken")
                    }.build())
                }
                    .also { client ->
                    if (BuildConfig.DEBUG) {
                        val login = HttpLoggingInterceptor()
                        login.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(login)
                    }

                }.build()//.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}