package com.example.musicsearchengine.api

import com.example.musicsearchengine.database.AlbumEntity
import com.example.musicsearchengine.model.Album

class NetworkAlbumContainer(val albums: List<Album>)

fun NetworkAlbumContainer.asDatabaseModel(): List<AlbumEntity> {
    return albums.map {
        AlbumEntity(
            collectionType =  it.collectionType,
            artistId = it.artistId,
            collectionId = it.collectionId,
            amgArtistId = it.amgArtistId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            collectionCensoredName = it.collectionCensoredName,
            artistViewUrl = it.artistViewUrl,
            collectionViewUrl = it.collectionViewUrl,
            artworkUrl60 = it.artworkUrl60,
            artworkUrl100 = it.artworkUrl100,
            collectionPrice = it.collectionPrice,
            collectionExplicitness = it.collectionExplicitness,
            trackCount = it.trackCount,
            copyright = it.copyright,
            country = it.country,
            currency = it.currency,
            releaseDate = it.releaseDate,
            primaryGenreName = it.primaryGenreName
        )
    }
}