package com.example.mymoviecatalogue2.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.vo.Resource


class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {

    lateinit var dataDetailMovie: LiveData<Resource<Movie>>
    lateinit var dataDetailTv: LiveData<Resource<TvShow>>

    fun getDetailMovie(catalogueId: Int): LiveData<Resource<Movie>> {
        dataDetailMovie = movieTvRepository.loadDetailMovie(catalogueId)
        return dataDetailMovie
    }

    fun getDetailTvShow(catalogueId: Int) : LiveData<Resource<TvShow>> {
        dataDetailTv = movieTvRepository.loadDetailTvShow(catalogueId)
        return dataDetailTv
    }

    fun setFavoriteMovie() {
        Log.d("setFav", "Inside ResourceMovie")
        val dataMovie = dataDetailMovie.value
        if(dataMovie?.data != null) {
            Log.d("SetFav", "InsideMovie")
            val newState = !dataMovie.data.isFav
            movieTvRepository.setFavMovie(dataMovie.data, newState)
        }
    }

    fun setFavoriteTv() {
        Log.d("setFav", "Inside ResourceMovie")
        val dataTv = dataDetailTv.value
        if (dataTv?.data != null) {
            Log.d("SetFav", "InsideMovie")
            val newState = !dataTv.data.isFav
            movieTvRepository.setFavTv(dataTv.data, newState)
        }
    }

}