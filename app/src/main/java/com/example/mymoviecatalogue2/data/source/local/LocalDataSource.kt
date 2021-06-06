package com.example.mymoviecatalogue2.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.local.room.MovieTvDao

class LocalDataSource private constructor(private val movieTvDao: MovieTvDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getINSTANCE(movieTvDao: MovieTvDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieTvDao).apply {
                INSTANCE = this
            }
    }

    fun getDataMovie(): DataSource.Factory<Int, Movie> =
        movieTvDao.getMovie()

    fun getDataTv(): DataSource.Factory<Int, TvShow> =
        movieTvDao.getTv()

    fun getFavMovie(): DataSource.Factory<Int, Movie> =
        movieTvDao.getFavMovie()

    fun getFavTv(): DataSource.Factory<Int, TvShow> =
        movieTvDao.getFavTv()

    fun getMovieId(id: Int): LiveData<Movie> = movieTvDao.getMovieId(id)

    fun getTvId(id: Int): LiveData<TvShow> = movieTvDao.getTvId(id)

    fun insertMovie(movie: List<Movie>) = movieTvDao.insertMovie(movie)

    fun insertTv(tv: List<TvShow>) = movieTvDao.insertTvShow(tv)

    fun updateFavMovie(movie: Movie, newState: Boolean) {
        movie.isFav = newState
        movieTvDao.updateMovie(movie)
    }

    fun updateFavTv(tv: TvShow, newState: Boolean) {
        tv.isFav = newState
        movieTvDao.updateTv(tv)
    }
}