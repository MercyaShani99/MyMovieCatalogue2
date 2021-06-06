package com.example.mymoviecatalogue2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResults(

    @field:SerializedName("id")
    val moviesId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genre_ids")
    val genre: List<Int>,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val imagePath: String,
)

data class MovieGenres(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
