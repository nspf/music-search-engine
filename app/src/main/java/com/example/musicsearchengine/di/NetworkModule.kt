package com.example.musicsearchengine.di

import com.example.musicsearchengine.BuildConfig.DEBUG
import com.example.musicsearchengine.model.*
import com.example.musicsearchengine.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout : Long = 60
    val readTimeout : Long  = 60

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(AlbumResponseItem::class.java, "wrapperType")
                .withSubtype(Album::class.java, WrapperType.collection.name)
                .withSubtype(AlbumArtist::class.java, WrapperType.artist.name)
        )
        .add(
            PolymorphicJsonAdapterFactory.of(SongResponseItem::class.java, "wrapperType")
                .withSubtype(Song::class.java, WrapperType.track.name)
                .withSubtype(SongsArtist::class.java, WrapperType.collection.name)
        )
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .client(client)
            .build()
    }

    single { provideMoshi() }
    single { provideHttpClient() }
    single {
        val baseUrl = Constants.BASE_URL
        provideRetrofit(get(), baseUrl)
    }
}