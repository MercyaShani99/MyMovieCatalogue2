package com.example.mymoviecatalogue2.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tvShow_data")
data class TvShow(

        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "id")
        val tvId: Int?,

        @ColumnInfo( name = "title")
        val title: String?,

        @ColumnInfo(name = "overview")
        val overview: String?,

        @ColumnInfo(name = "release_date")
        val releaseDate: String?,

        @ColumnInfo(name = "poster_path")
        val imagePath: String?,

        @ColumnInfo(name = "isFav")
        var isFav: Boolean = false
)
