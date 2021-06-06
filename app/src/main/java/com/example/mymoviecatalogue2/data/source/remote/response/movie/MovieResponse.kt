package com.example.mymoviecatalogue2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<MovieResults>

)


