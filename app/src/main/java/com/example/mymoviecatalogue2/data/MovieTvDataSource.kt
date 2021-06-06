package com.example.mymoviecatalogue2.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.vo.Resource

interface MovieTvDataSource {

    fun getMoviePopular(): LiveData<Resource<PagedList<Movie>>>

    fun getTvPopular(): LiveData<Resource<PagedList<TvShow>>>

    fun getDetailMovie(idMovie: Int): LiveData<Resource<Movie>>

    fun getDetailTvShow(idTvShow: Int): LiveData<Resource<TvShow>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTv(tv: TvShow, state: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<Movie>>

    fun getFavoriteTv(): LiveData<PagedList<TvShow>>
}