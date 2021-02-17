package com.example.musicsearchengine.di

import com.example.musicsearchengine.ui.albums.AlbumsViewModel
import com.example.musicsearchengine.ui.favorites.FavoriteSongsViewModel
import com.example.musicsearchengine.ui.search.SearchViewModel
import com.example.musicsearchengine.ui.songpreview.SongPreviewViewModel
import com.example.musicsearchengine.ui.songs.SongsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { (id: Long) -> AlbumsViewModel(id, get(), get()) }
    viewModel { (collectionId: Long) -> SongsViewModel(collectionId, get()) }
    viewModel { (trackId: Long) -> SongPreviewViewModel(trackId, get()) }
    viewModel { FavoriteSongsViewModel(get()) }
}