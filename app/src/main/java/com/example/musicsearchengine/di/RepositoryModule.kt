package com.example.musicsearchengine.di

import com.example.musicsearchengine.api.ApiService
import com.example.musicsearchengine.database.MusicDatabaseDao
import com.example.musicsearchengine.repository.MusicRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(api: ApiService, dao : MusicDatabaseDao): MusicRepository {
        return MusicRepository(api, dao)
    }
    single { provideRepository(get(), get()) }

}