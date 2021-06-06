package com.example.mymoviecatalogue2.data.source.remote.api

import com.example.mymoviecatalogue2.data.source.remote.response.*
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResponse
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey:String
    ): Call<MovieResponse>

    @GET("tv/popular")
    fun getTv(
        @Query("api_key") apiKey: String
    ): Call<TvResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailResults>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailTvResults>

    @GET("genre/movie/list?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getMovieGenre() : Call<GenreResponse>

    @GET("genre/tv/list?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getTvGenre() : Call<GenreResponse>
}