package com.example.musicsearchengine.di

import android.app.Application
import androidx.room.Room
import com.example.musicsearchengine.database.MusicDatabase
import com.example.musicsearchengine.database.MusicDatabaseDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): MusicDatabase {
        return Room.databaseBuilder(application, MusicDatabase::class.java, "music")
                .fallbackToDestructiveMigration()
                .build()
    }

    fun provideCountriesDao(database: MusicDatabase): MusicDatabaseDao {
        return  database.musicDatabaseDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}