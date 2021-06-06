package com.example.mymoviecatalogue2.data.source.remote.response.detail

import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres
import com.google.gson.annotations.SerializedName

data class DetailResults (
    @field:SerializedName("id")
    val moviesId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genres")
    val genre: List<MovieGenres>,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val imagePath: String,
)
