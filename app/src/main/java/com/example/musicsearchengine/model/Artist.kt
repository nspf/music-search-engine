package com.example.musicsearchengine.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
        val artistId: Long,
        val wrapperType: String?,
        val artistType: String?,
        val artistName: String?,
        val artistLinkUrl: String?,
        val amgArtistId: Int?,
        val primaryGenreName: String?,
        val primaryGenreId: Int?
)

