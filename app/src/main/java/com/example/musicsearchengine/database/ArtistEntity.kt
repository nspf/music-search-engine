package com.example.musicsearchengine.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
        tableName = "artist_table",
        indices = [Index(value = ["artist_id"], unique = true)])
data class ArtistEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        @ColumnInfo(name = "artist_id")
        val artistId: Long,
        @ColumnInfo(name = "wrapper_type")
        val wrapperType: String?,
        @ColumnInfo(name = "artist_type")
        val artistType: String?,
        @ColumnInfo(name = "artist_name")
        val artistName: String?,
        @ColumnInfo(name = "artist_link_url")
        val artistLinkUrl: String?,
        @ColumnInfo(name = "amg_artist_id")
        val amgArtistId: Int?,
        @ColumnInfo(name = "primary_gender_name")
        val primaryGenreName: String?,
        @ColumnInfo(name = "primary_gender_id")
        val primaryGenreId: Int?,
        @ColumnInfo(name = "search_term")
        val searchTerm: String?,
): Parcelable