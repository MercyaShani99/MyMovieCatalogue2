package com.example.mymoviecatalogue2.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.vo.Resource


class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {

    private var catalogueId = MutableLiveData<Int>()

    fun setDetailMovie(catalogueId: Int) {
        this.catalogueId.value = catalogueId
    }

    fun setDetailTv(catalogueId: Int) {
        this.catalogueId.value = catalogueId
    }

    var getDetailMovie: LiveData<Resource<Movie>> = Transformations.switchMap(catalogueId) {
        movieTvRepository.getDetailMovie(it) }

    var getDetailTvShow: LiveData<Resource<TvShow>> = Transformations.switchMap(catalogueId) {
        movieTvRepository.getDetailTvShow(it) }


    fun setFavoriteMovie() {
        Log.d("setFav", "Inside ResourceMovie")
        val dataMovie = getDetailMovie.value
        if (dataMovie != null) {
            val entity = dataMovie.data

            if (entity != null) {
                val newState = !entity.isFav
                movieTvRepository.setFavoriteMovie(entity, newState)
                }
            }
        }

    fun setFavoriteTv() {
        Log.d("setFav", "Inside ResourceMovie")
        val dataTv = getDetailTvShow.value
        if (dataTv != null) {
            val entity = dataTv.data

            if (entity != null) {
                val newState = !entity.isFav
                movieTvRepository.setFavoriteTv(entity, newState)
            }
        }
    }
}
