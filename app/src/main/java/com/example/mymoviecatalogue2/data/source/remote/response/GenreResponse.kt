package com.example.mymoviecatalogue2.data.source.remote.response

import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres
import com.google.gson.annotations.SerializedName

data class GenreResponse (
    @field:SerializedName("genres")
    val genres: List<MovieGenres>
)