package com.example.musicsearchengine.ui.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicsearchengine.repository.MusicRepository
import kotlinx.coroutines.launch

class SongsViewModel(
        collectionId: Long,
        private val repository: MusicRepository
) : ViewModel() {

    var songsLiveData = repository.getSongsByAlbum(collectionId)

    init {
        viewModelScope.launch {
            repository.fetchSongsByAlbum(collectionId)
        }
    }

}