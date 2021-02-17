package com.example.musicsearchengine.api

import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.model.Artist

class NetworkArtistContainer(val artists: List<Artist>)

fun NetworkArtistContainer.asDatabaseModel(search: String): List<ArtistEntity> {
    return artists.map {
        ArtistEntity(
            artistId = it.artistId,
            wrapperType = it.wrapperType,
            artistType = it.artistType,
            artistName = it.artistName,
            artistLinkUrl = it.artistLinkUrl,
            amgArtistId = it.amgArtistId,
            primaryGenreName = it.primaryGenreName,
            primaryGenreId = it.primaryGenreId,
            searchTerm = search
        )
    }
}

fun NetworkArtistContainer.asDomainModel(): List<Artist> {
    return artists.map {
        Artist(
            artistId = it.artistId,
            wrapperType = it.wrapperType,
            artistType = it.artistType,
            artistName = it.artistName,
            artistLinkUrl = it.artistLinkUrl,
            amgArtistId = it.amgArtistId,
            primaryGenreName = it.primaryGenreName,
            primaryGenreId = it.primaryGenreId
        )
    }
}