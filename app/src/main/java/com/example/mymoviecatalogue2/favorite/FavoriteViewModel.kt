package com.example.mymoviecatalogue2.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow

class FavoriteViewModel(private val movieTvRepository: MovieTvRepository): ViewModel() {

    fun getListFavMovie(): LiveData<PagedList<Movie>> = movieTvRepository.getFavMovie()

    fun setListFavMovie(movieEntity: Movie) {
        val newState = !movieEntity.isFav
        movieTvRepository.setFavMovie(movieEntity, newState)
    }

    fun getListFavTv(): LiveData<PagedList<TvShow>> = movieTvRepository.getFavTv()

    fun setListFavTv(tvShowEntity: TvShow) {
        val newState = !tvShowEntity.isFav
        movieTvRepository.setFavTv(tvShowEntity, newState)
    }
}