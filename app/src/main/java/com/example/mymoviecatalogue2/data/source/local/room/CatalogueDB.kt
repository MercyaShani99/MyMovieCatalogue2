package com.example.mymoviecatalogue2.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow

@Database(
    entities = [Movie::class, TvShow::class],
    version = 3,
    exportSchema = false
)
abstract class CatalogueDB: RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueDB? = null

        fun getInstance(context: Context): CatalogueDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueDB::class.java,
                    "film.db"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }
    }
}