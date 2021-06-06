package com.example.mymoviecatalogue2.data.source.remote.api

import com.example.mymoviecatalogue2.data.source.remote.response.*
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResponse
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getMovies(): Call<MovieResponse>

    @GET("tv/popular?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getTv(): Call<TvResponse>

    @GET("movie/{movie_id}?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getDetailMovie(
        @Path("movie_id") id: Int
    ): Call<DetailResults>

    @GET("tv/{tv_id}?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getDetailTv(
        @Path("tv_id") id: Int,
    ): Call<DetailTvResults>

    @GET("genre/movie/list?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getMovieGenre() : Call<GenreResponse>

    @GET("genre/tv/list?api_key=c44284fb2fadf0bfb7e77ef1805b48cb")
    fun getTvGenre() : Call<GenreResponse>
}