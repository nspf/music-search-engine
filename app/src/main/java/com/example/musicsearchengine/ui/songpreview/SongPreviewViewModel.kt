package com.example.musicsearchengine.ui.songpreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicsearchengine.repository.MusicRepository
import kotlinx.coroutines.launch

class SongPreviewViewModel (
    trackId: Long,
    private val repository: MusicRepository) : ViewModel() {

    val song = repository.getSong(trackId)
    
    fun toggleFavoriteSong() {
        viewModelScope.launch {
            val currentSong = song.value
            if (currentSong!= null) {
                repository.toggleFavoriteSong(currentSong)
            }
        }
    }

}