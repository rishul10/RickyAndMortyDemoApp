package com.example.rickyandmortydemoapp.di.module

import com.example.rickyandmortydemoapp.network.ApiInterface
import com.example.rickyandmortydemoapp.utils.ApplicationConstant.CONTENT_TYPE
import com.example.rickyandmortydemoapp.utils.ApplicationConstant.SERVER_URL
import com.example.rickyandmortydemoapp.utils.ApplicationConstant.TIMEOUT_CONNECTION
import com.example.rickyandmortydemoapp.utils.ApplicationConstant.TIMEOUT_READ
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT_CONNECTION.toLong(), TimeUnit.MINUTES)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", CONTENT_TYPE)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }

        return httpClient.build()
    }
}