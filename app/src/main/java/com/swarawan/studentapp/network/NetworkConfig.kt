package com.swarawan.studentapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rio Swarawan on 10/26/18.
 */
class NetworkConfig {

    private fun okHttpConfiguration(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(getLoggingInterceptor())
                .build()
    }

    private fun retrofitConfiguration(): Retrofit {
        return Retrofit.Builder()
                .client(okHttpConfiguration())
                .baseUrl("http://kotlinspringcrud.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun getServiceApi(): ServiceApi {
        return retrofitConfiguration().create(ServiceApi::class.java)
    }
}