package com.example.musicsearchengine.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistsResponse (
        var resultCount: Int?,
        var results: List<Artist> = listOf()
)