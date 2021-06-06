package com.example.mymoviecatalogue2.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.vo.Resource


class TvShowViewModel(private val MovieTvRepository: MovieTvRepository) : ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvShow>>> = MovieTvRepository.loadAllTvShow()
}