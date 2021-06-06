package com.example.mymoviecatalogue2.data.source.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TvResults(

    @field:SerializedName("id")
    val tvId: Int,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("genre_ids")
    val genre: List<Int>,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val imagePath: String,
)
