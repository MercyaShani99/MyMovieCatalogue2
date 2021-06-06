package com.example.mymoviecatalogue2.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
@Dao
interface CatalogueDao {


    @Query("SELECT * FROM movie_data")
    fun getMovie(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tvShow_data")
    fun getTv(): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM movie_data WHERE isFav = 1")
    fun getFavMovie(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tvShow_data WHERE isFav = 1")
    fun getFavTv(): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM movie_data WHERE id= :id")
    fun getMovieId(id:Int): LiveData<Movie>

    @Query("SELECT * FROM tvShow_data WHERE id= :id")
    fun getTvId(id: Int): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShow>)

    @Update
    fun updateMovie(movie: Movie)

    @Update
    fun updateTv(tv: TvShow)
}