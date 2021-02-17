package com.example.musicsearchengine.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
        tableName = "song_table",
        indices = [Index(value = ["track_id"], unique = true)])
data class SongEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        @ColumnInfo(name = "kind")
        val kind: String,
        @ColumnInfo(name = "artist_id")
        val artistId: Int,
        @ColumnInfo(name = "collection_id")
        val collectionId: Int,
        @ColumnInfo(name = "track_id")
        val trackId: Int,
        @ColumnInfo(name = "artist_name")
        val artistName: String,
        @ColumnInfo(name = "collection_name")
        val collectionName: String,
        @ColumnInfo(name = "track_name")
        val trackName: String,
        @ColumnInfo(name = "collection_censored_name")
        val collectionCensoredName: String,
        @ColumnInfo(name = "track_censored_name")
        val trackCensoredName: String,
        @ColumnInfo(name = "artist_view_url")
        val artistViewUrl: String,
        @ColumnInfo(name = "collection_view_url")
        val collectionViewUrl: String,
        @ColumnInfo(name = "track_view_url")
        val trackViewUrl: String,
        @ColumnInfo(name = "preview_url")
        val previewUrl: String,
        @ColumnInfo(name = "artwork_url_30")
        val artworkUrl30: String,
        @ColumnInfo(name = "artwork_url_60")
        val artworkUrl60: String,
        @ColumnInfo(name = "artwork_url_100")
        val artworkUrl100: String,
        @ColumnInfo(name = "collection_price")
        val collectionPrice: Double,
        @ColumnInfo(name = "track_price")
        val trackPrice: Double,
        @ColumnInfo(name = "release_date")
        val releaseDate: String,
        @ColumnInfo(name = "collection_explicitness")
        val collectionExplicitness: String,
        @ColumnInfo(name = "track_explicitness")
        val trackExplicitness: String,
        @ColumnInfo(name = "disc_count")
        val discCount: Int,
        @ColumnInfo(name = "disc_number")
        val discNumber: Int,
        @ColumnInfo(name = "track_count")
        val trackCount: Int,
        @ColumnInfo(name = "track_number")
        val trackNumber: Int,
        @ColumnInfo(name = "track_time_millis")
        val trackTimeMillis: Int,
        @ColumnInfo(name = "country")
        val country: String,
        @ColumnInfo(name = "currency")
        val currency: String,
        @ColumnInfo(name = "primary_genre_name")
        val primaryGenreName: String,
        @ColumnInfo(name = "is_streamable")
        val isStreamable: Boolean?,
        val isFavorite: Boolean = false
): Parcelable