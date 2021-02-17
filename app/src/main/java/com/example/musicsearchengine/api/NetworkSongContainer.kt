package com.example.musicsearchengine.api

import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.model.Song

class NetworkSongContainer(val songs: List<Song>)

fun NetworkSongContainer.asDatabaseModel(): List<SongEntity> {
    return songs.map {
        SongEntity(
                kind = it.kind,
                artistId = it.artistId,
                collectionId = it.collectionId,
                trackId = it.trackId,
                artistName = it.artistName,
                collectionName = it.collectionName,
                trackName = it.trackName,
                collectionCensoredName = it.collectionCensoredName,
                trackCensoredName = it.trackCensoredName,
                artistViewUrl = it.artistViewUrl,
                collectionViewUrl = it.collectionViewUrl,
                trackViewUrl = it.trackViewUrl,
                previewUrl = it.previewUrl,
                artworkUrl30 = it.artworkUrl30,
                artworkUrl60 = it.artworkUrl60,
                artworkUrl100 = it.artworkUrl100,
                collectionPrice = it.collectionPrice,
                trackPrice = it.trackPrice,
                releaseDate = it.releaseDate,
                collectionExplicitness = it.collectionExplicitness,
                trackExplicitness = it.trackExplicitness,
                discCount = it.discCount,
                discNumber = it.discNumber,
                trackCount = it.trackCount,
                trackNumber = it.trackNumber,
                trackTimeMillis = it.trackTimeMillis,
                country = it.country,
                currency = it.currency,
                primaryGenreName = it.primaryGenreName,
                isStreamable = it.isStreamable
        )
    }
}