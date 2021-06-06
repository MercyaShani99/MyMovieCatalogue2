package com.example.mymoviecatalogue2.data.source.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TvResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<TvResults>
)


