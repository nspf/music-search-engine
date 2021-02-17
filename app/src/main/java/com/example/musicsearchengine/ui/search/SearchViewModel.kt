package com.example.musicsearchengine.ui.search

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.musicsearchengine.api.ApiStatus
import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.repository.Listing
import com.example.musicsearchengine.repository.MusicRepository

class SearchViewModel(private val repository: MusicRepository) : ViewModel() {

    var lastSearchQuery = ""

    val searchLiveData = MutableLiveData<String>()

    private var listingLiveData: LiveData<Listing<ArtistEntity>> =
        Transformations.map(searchLiveData) {
            repository.artistSearch(it)
        }

    val apiStatus: LiveData<ApiStatus> =
        Transformations.switchMap(listingLiveData) {
            it.networkState
    }

    val searchResults: LiveData<PagedList<ArtistEntity>> =
        Transformations.switchMap(listingLiveData) {
            it.pagedList
        }






}