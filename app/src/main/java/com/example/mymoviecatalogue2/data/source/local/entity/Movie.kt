package com.example.mymoviecatalogue2.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie_data")
data class Movie(
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "id")
        val movieId: Int?,

        @ColumnInfo(name ="title")
        val title: String?,

        @ColumnInfo(name = "overview")
        val overview: String?,

        @ColumnInfo(name = "poster_path")
        val imagePath: String?,

        @ColumnInfo(name = "isFav")
        var isFav: Boolean = false
)