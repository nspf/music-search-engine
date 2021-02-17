package com.example.musicsearchengine.api

import com.example.musicsearchengine.utils.Constants.SEARCH_ENTITY
import com.example.musicsearchengine.utils.Constants.SEARCH_ATTRIBUTES
import com.example.musicsearchengine.model.*
import com.example.musicsearchengine.utils.Constants.ALBUM_ENTITY
import com.example.musicsearchengine.utils.Constants.SEARCH_PAGE_OFFSET
import com.example.musicsearchengine.utils.Constants.SONG_ENTITY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search?entity=${SEARCH_ENTITY}&limit=${SEARCH_PAGE_OFFSET}&attribute=${SEARCH_ATTRIBUTES}")
    suspend fun artistSearch(
            @Query("term") searchTerm: String,
            @Query("offset") offset: String
    ): ArtistsResponse

    @GET("lookup?&entity=${ALBUM_ENTITY}")
    suspend fun getAlbumsByArtistId(
            @Query("id") artistId: String
    ): AlbumResponse

    @GET("lookup?&entity=${SONG_ENTITY}&country=US")
    suspend fun getSongsByAlbum(
            @Query("id") collectionId: String
    ): SongsResponse

}