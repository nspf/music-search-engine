package com.example.musicsearchengine.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongsResponse (
        var resultCount: Int?,
        var results: List<SongResponseItem> = listOf()
)