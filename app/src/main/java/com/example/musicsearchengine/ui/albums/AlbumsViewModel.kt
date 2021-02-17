package com.example.musicsearchengine.ui.albums

import androidx.lifecycle.*
import androidx.paging.toLiveData
import com.example.musicsearchengine.database.MusicDatabaseDao
import com.example.musicsearchengine.repository.MusicRepository
import kotlinx.coroutines.launch

class AlbumsViewModel(
        id: Long,
        dao: MusicDatabaseDao,
        private val repository: MusicRepository
) : ViewModel() {

    var albums = dao.getAlbumsByArtist(id).toLiveData(pageSize = 20)

    init {
        getAlbumsByArtist(id)
    }

    private fun getAlbumsByArtist(artistId: Long) {
        viewModelScope.launch {
            repository.fetchAlbumsByArtist(artistId)
        }
    }

}