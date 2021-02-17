package com.example.musicsearchengine.data

import androidx.lifecycle.LiveData
import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.repository.Listing

interface MusicDataSource {
    fun artistSearch(search: String): Listing<ArtistEntity>
    suspend fun fetchAlbumsByArtist(artistId: Long)
    suspend fun fetchSongsByAlbum(collectionId: Long)
    fun getSongsByAlbum(collectionId: Long): LiveData<List<SongEntity>>
    fun getFavoriteSongs(): LiveData<List<SongEntity>>
    fun getSong(trackId: Long): LiveData<SongEntity>
    suspend fun toggleFavoriteSong(song: SongEntity)
}