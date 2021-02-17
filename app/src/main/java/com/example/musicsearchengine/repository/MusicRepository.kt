package com.example.musicsearchengine.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.musicsearchengine.api.*
import com.example.musicsearchengine.data.MusicDataSource
import com.example.musicsearchengine.data.SearchBoundaryCallback
import com.example.musicsearchengine.database.ArtistEntity
import com.example.musicsearchengine.database.MusicDatabaseDao
import com.example.musicsearchengine.database.SongEntity
import com.example.musicsearchengine.model.Album
import com.example.musicsearchengine.model.Song
import com.example.musicsearchengine.utils.Constants.SEARCH_PAGE_OFFSET
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepository(
    private val apiService: ApiService,
    private val dao: MusicDatabaseDao): MusicDataSource {

    private var status = MutableLiveData<ApiStatus>()

    override fun artistSearch(search: String): Listing<ArtistEntity> {
        val dataSource: DataSource.Factory<Int, ArtistEntity> = dao.getArtistsByName(search)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(SEARCH_PAGE_OFFSET)
            .build()
        val callback = SearchBoundaryCallback(apiService, dao, search)
        val pagedList = LivePagedListBuilder(dataSource, config)
            .setBoundaryCallback(callback)
            .build()

        return Listing(
            pagedList = pagedList,
            refresh = null,
            networkState = callback.apiStatus,
            refreshState = null
        )
    }

    override suspend fun fetchAlbumsByArtist(artistId: Long) {
        withContext(Dispatchers.IO) {
            try {
                val albumsResponse = apiService.getAlbumsByArtistId(artistId.toString())
                status.postValue(ApiStatus.LOADING)
                dao.insertAllAlbums(
                        NetworkAlbumContainer(
                                albumsResponse.results.filterIsInstance<Album>()
                        ).asDatabaseModel()
                )
                status.postValue(ApiStatus.DONE)
            } catch (e: Exception) {
                status.postValue(ApiStatus.ERROR)
                e.printStackTrace()
            }
        }
    }

    override suspend fun fetchSongsByAlbum(collectionId: Long) {
        withContext(Dispatchers.IO) {
            try {
                val albumsResponse = apiService.getSongsByAlbum(collectionId.toString())
                status.postValue(ApiStatus.LOADING)
                dao.insertSongs(
                        NetworkSongContainer(
                                albumsResponse.results.filterIsInstance<Song>()
                        ).asDatabaseModel()
                )
                status.postValue(ApiStatus.DONE)
            } catch (e: Exception) {
                status.postValue(ApiStatus.ERROR)
                e.printStackTrace()
            }
        }
    }

    override fun getSongsByAlbum(collectionId: Long) = dao.getSongsByCollectionId(collectionId)

    override fun getFavoriteSongs() = dao.getFavoriteSongs()

    override fun getSong(trackId: Long) = dao.getSong(trackId)

    override suspend fun toggleFavoriteSong(song: SongEntity) {
        withContext(Dispatchers.IO) {
            dao.toggleFavoriteSong(!song.isFavorite, song.id)
        }
    }

}

