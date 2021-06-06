package com.example.mymoviecatalogue2.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.vo.Resource

class MovieViewModel(private val MovieTvRepository: MovieTvRepository) : ViewModel(){

    fun getMovie(): LiveData<Resource<PagedList<Movie>>> = MovieTvRepository.getMoviePopular()


}