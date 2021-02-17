package com.example.musicsearchengine.model

/*
https://proandroiddev.com/moshi-polymorphic-adapter-is-d25deebbd7c5
 */
sealed class SongResponseItem

data class Song (
        val kind : String,
        val artistId : Int,
        val collectionId : Int,
        val trackId : Int,
        val artistName : String,
        val collectionName : String,
        val trackName : String,
        val collectionCensoredName : String,
        val trackCensoredName : String,
        val artistViewUrl : String,
        val collectionViewUrl : String,
        val trackViewUrl : String,
        val previewUrl : String,
        val artworkUrl30 : String,
        val artworkUrl60 : String,
        val artworkUrl100 : String,
        val collectionPrice : Double,
        val trackPrice : Double,
        val releaseDate : String,
        val collectionExplicitness : String,
        val trackExplicitness : String,
        val discCount : Int,
        val discNumber : Int,
        val trackCount : Int,
        val trackNumber : Int,
        val trackTimeMillis : Int,
        val country : String,
        val currency : String,
        val primaryGenreName : String,
        val isStreamable : Boolean?
): SongResponseItem()

data class SongsArtist(
        val artistId: Long,
        val artistType: String?,
        val artistName: String?,
        val artistLinkUrl: String?,
        val amgArtistId: Int?,
        val primaryGenreName: String?,
        val primaryGenreId: Int?
) : SongResponseItem()
