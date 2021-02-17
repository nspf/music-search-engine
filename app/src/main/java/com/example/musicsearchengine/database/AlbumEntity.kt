package com.example.musicsearchengine.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
        tableName = "album_table",
        indices = [Index(value = ["collection_id"], unique = true)])
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "collection_id")
    val collectionId: Long,
    @ColumnInfo(name = "collection_type")
    val collectionType: String?,
    @ColumnInfo(name = "artist_id")
    val artistId: Long?,
    @ColumnInfo(name = "amg_artist_id")
    val amgArtistId: Int?,
    @ColumnInfo(name = "artist_name")
    val artistName: String?,
    @ColumnInfo(name = "collection_name")
    val collectionName: String?,
    @ColumnInfo(name = "collection_censored_name")
    val collectionCensoredName: String?,
    @ColumnInfo(name = "artist_view_url")
    val artistViewUrl: String?,
    @ColumnInfo(name = "collection_view_url")
    val collectionViewUrl: String?,
    @ColumnInfo(name = "artwork_url_60")
    val artworkUrl60: String?,
    @ColumnInfo(name = "artwork_url_100")
    val artworkUrl100: String?,
    @ColumnInfo(name = "collection_price")
    val collectionPrice: Double?,
    @ColumnInfo(name = "collection_explicitness")
    val collectionExplicitness: String?,
    @ColumnInfo(name = "track_count")
    val trackCount: Int?,
    @ColumnInfo(name = "copyright")
    val copyright: String?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "currency")
    val currency: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "primary_genre_name")
    val primaryGenreName: String?
): Parcelable