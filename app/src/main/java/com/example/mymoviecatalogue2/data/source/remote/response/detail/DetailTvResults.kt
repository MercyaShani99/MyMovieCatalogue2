package com.example.mymoviecatalogue2.data.source.remote.response.detail

import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres
import com.google.gson.annotations.SerializedName

data class DetailTvResults (
    @field:SerializedName("id")
    val tvId: Int,

    @field:SerializedName("original_name")
    val title: String,

    @field:SerializedName("genres")
    val genre: List<MovieGenres>,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val imagePath: String,
)
