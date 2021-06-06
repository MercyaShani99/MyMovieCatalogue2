package com.example.mymoviecatalogue2.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getINSTANCE(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao).apply {
                INSTANCE = this
            }
    }

    fun getDataMovie(): DataSource.Factory<Int, Movie> =
        catalogueDao.getMovie()

    fun getDataTv(): DataSource.Factory<Int, TvShow> =
        catalogueDao.getTv()

    fun getFavMovie(): DataSource.Factory<Int, Movie> =
        catalogueDao.getFavMovie()

    fun getFavTv(): DataSource.Factory<Int, TvShow> =
        catalogueDao.getFavTv()

    fun getMovieId(id: Int): LiveData<Movie> = catalogueDao.getMovieId(id)

    fun getTvId(id: Int): LiveData<TvShow> = catalogueDao.getTvId(id)

    fun insertMovie(movie: List<Movie>) = catalogueDao.insertMovie(movie)

    fun insertTv(tv: List<TvShow>) = catalogueDao.insertTvShow(tv)

    fun updateFavMovie(movie: Movie, newState: Boolean) {
        movie.isFav = newState
        catalogueDao.updateMovie(movie)
    }

    fun updateFavTv(tv: TvShow, newState: Boolean) {
        tv.isFav = newState
        catalogueDao.updateTv(tv)
    }
}