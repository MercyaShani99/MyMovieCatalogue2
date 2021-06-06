package com.example.mymoviecatalogue2.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.vo.Resource

interface MovieTvDataSource {

    fun getMoviePopular(): LiveData<Resource<PagedList<Movie>>>

    fun loadAllTvShow(): LiveData<Resource<PagedList<TvShow>>>

    fun loadDetailMovie(idMovie: Int): LiveData<Resource<Movie>>

    fun loadDetailTvShow(idTvShow: Int): LiveData<Resource<TvShow>>

    fun setFavMovie(movie: Movie, state: Boolean)

    fun setFavTv(tv: TvShow, state: Boolean)

    fun getFavMovie(): LiveData<PagedList<Movie>>

    fun getFavTv(): LiveData<PagedList<TvShow>>
}