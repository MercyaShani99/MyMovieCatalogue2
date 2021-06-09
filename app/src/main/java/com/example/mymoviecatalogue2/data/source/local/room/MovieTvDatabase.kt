package com.example.mymoviecatalogue2.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow

@Database(
    entities = [Movie::class, TvShow::class],
    version = 7,
    exportSchema = false
)
abstract class MovieTvDatabase: RoomDatabase() {
    abstract fun catalogueDao(): MovieTvDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvDatabase? = null

        fun getInstance(context: Context): MovieTvDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvDatabase::class.java,
                    "film.db"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }
    }
}