package com.example.musicsearchengine.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList.BoundaryCallback
import com.example.musicsearchengine.api.*
import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.database.MusicDatabaseDao
import com.example.musicsearchengine.utils.Constants
import kotlinx.coroutines.*

class SearchBoundaryCallback(
        private val apiService: ApiService,
        private val dao: MusicDatabaseDao,
        private val search: String
) : BoundaryCallback<ArtistEntity>() {

    val apiStatus = MutableLiveData<ApiStatus>()

    private var page = 0

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        apiStatus.value = ApiStatus.LOADING
        GlobalScope.launch { artistSearch(search, 0) }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ArtistEntity) {
        super.onItemAtEndLoaded(itemAtEnd)
        apiStatus.value = ApiStatus.LOADING
        page++
        GlobalScope.launch { artistSearch(search, page * Constants.SEARCH_PAGE_OFFSET) }
    }

    private suspend fun artistSearch(search: String, offset: Int) {
        withContext(Dispatchers.IO) {
            try {
                val artistResponse = apiService.artistSearch(search, offset.toString())
                apiStatus.postValue(ApiStatus.DONE)
                dao.insertAllArtists(
                        NetworkArtistContainer(artistResponse.results).asDatabaseModel(search))
            } catch (e: Exception) {
                apiStatus.postValue(ApiStatus.ERROR)
                e.printStackTrace()
            }
        }
    }

}