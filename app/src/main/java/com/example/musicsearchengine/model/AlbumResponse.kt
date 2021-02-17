package com.example.musicsearchengine.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResponse (
    var resultCount: Int?,
    var results: List<AlbumResponseItem> = listOf()
)