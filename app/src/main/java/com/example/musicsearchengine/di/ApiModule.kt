package com.example.musicsearchengine.di

import com.example.musicsearchengine.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideApi(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
    single { provideApi(get()) }

}