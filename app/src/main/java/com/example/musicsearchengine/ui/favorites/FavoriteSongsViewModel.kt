package com.example.musicsearchengine.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.musicsearchengine.repository.MusicRepository

class FavoriteSongsViewModel(repository: MusicRepository) : ViewModel() {

    var songsLiveData = repository.getFavoriteSongs()

}