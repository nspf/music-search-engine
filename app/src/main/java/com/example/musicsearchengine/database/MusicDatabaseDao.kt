package com.example.musicsearchengine.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface MusicDatabaseDao {

    // Artists
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllArtists(artists: List<ArtistEntity>)

    @Transaction
    fun updateArtistList(artists: List<ArtistEntity>) {
        deleteAllArtists()
        insertAllArtists(artists)
    }

    @Query("DELETE FROM artist_table")
    fun deleteAllArtists()

    @Query("SELECT * from artist_table where search_term = :search order by id ASC")
    fun getArtistsByName(search: String): DataSource.Factory<Int, ArtistEntity>

    // Albums
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbum(albumEntity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllAlbums(albums: List<AlbumEntity>)

    @Query("DELETE FROM album_table")
    fun deleteAllAlbums()

    @Query("SELECT * from album_table where artist_id = :artistId order by id ASC")
    fun getAlbumsByArtist(artistId: Long): DataSource.Factory<Int, AlbumEntity>

    // Songs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSongs(songs: List<SongEntity>)

    @Query("SELECT * from song_table where collection_id = :collectionId order by id ASC")
    fun getSongsByCollectionId(collectionId: Long): LiveData<List<SongEntity>>

    @Query("SELECT * from song_table where isFavorite = 1 order by id ASC")
    fun getFavoriteSongs(): LiveData<List<SongEntity>>

    // Song
    @Query("SELECT * from song_table where track_id = :trackId limit 1")
    fun getSong(trackId: Long): LiveData<SongEntity>

    @Query("UPDATE song_table SET isFavorite=:isFavorite WHERE id = :id")
    fun toggleFavoriteSong(isFavorite: Boolean, id: Long)

}