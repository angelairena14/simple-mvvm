package com.example.moneymanager.di

import com.example.moneymanager.repository.RetrofitRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule constructor(baseUrl : String){
    var baseUrl : String? = ""

    init {
        this.baseUrl = baseUrl
    }

    @Singleton
    @Provides
    fun provideOKHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(1200,TimeUnit.SECONDS)
            .connectTimeout(1200,TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGSON() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRetroRepository() : RetrofitRepository {
        return RetrofitRepository()
    }
}
