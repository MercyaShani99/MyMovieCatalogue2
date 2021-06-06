package com.example.mymoviecatalogue2.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {

        val dummyMovie = DataDummy.generateDummyMovie()
        val listMovie =MutableLiveData<List<Movie>>()
        listMovie.value = dummyMovie

        `when`(movieRepository.loadAllMovies()).thenReturn(listMovie)
        val movieEntity = viewModel.getMovie().value
        verify(movieRepository).loadAllMovies()
        assertEquals(10, movieEntity?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}