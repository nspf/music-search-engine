package com.example.musicsearchengine.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ArtistEntity::class, AlbumEntity::class, SongEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val musicDatabaseDao: MusicDatabaseDao
}