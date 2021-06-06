package com.example.mymoviecatalogue2.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.utils.DataDummy
import com.example.mymoviecatalogue2.utils.DetailDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val listMovie = DataDummy.generateDummyMovie()[0]
    private val listTvShow = DataDummy.generateDummyTvShow()[0]
    private val detailMovieDummy = DetailDummy.getDetailMovie()
    private val idMovie = detailMovieDummy.movieId.toString()
    private val detailTvShowDummy = DetailDummy.getDetailTv()
    private val idTvShow = detailTvShowDummy.tvId.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var movieObserver: Observer<Movie>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShow>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(movieTvRepository)
    }

    @Test
    fun getDetailMovie() {
        val movies = MutableLiveData<Movie>()
        movies.value = detailMovieDummy

        `when`(movieTvRepository.loadDetailMovie(idMovie)).thenReturn(movies)

        val movie = detailViewModel.getDetailMovie(idMovie).value as Movie

        verify(movieTvRepository).loadDetailMovie(idMovie)
        assertNotNull(movie)

        detailMovieDummy.apply {
            assertEquals(listMovie.movieId, movie.movieId)
            assertEquals(listMovie.genre, movie.genre)
            assertEquals(listMovie.title, movie.title)
            assertEquals(listMovie.overview, movie.overview)
            assertEquals(listMovie.imagePath, movie.imagePath)
        }


        detailViewModel.getDetailMovie(idMovie).observeForever(movieObserver)
        verify(movieObserver).onChanged(detailMovieDummy)
    }

    @Test
    fun getDetailTvShow() {
        val tvShows = MutableLiveData<TvShow>()
        tvShows.value = detailTvShowDummy

        `when`(movieTvRepository.loadDetailTvShow(idTvShow)).thenReturn(tvShows)

        val tvShow = detailViewModel.getDetailTvShow(idTvShow).value as TvShow

        verify(movieTvRepository).loadDetailTvShow(idTvShow)
        assertNotNull(tvShow)

        detailTvShowDummy.apply {
            assertEquals(listTvShow.tvId, tvShow.tvId)
            assertEquals(listTvShow.genre, tvShow.genre)
            assertEquals(listTvShow.title, tvShow.title)
            assertEquals(listTvShow.overview, tvShow.overview)
            assertEquals(listTvShow.imagePath, tvShow.imagePath)
        }


        detailViewModel.getDetailTvShow(idTvShow).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(detailTvShowDummy)

    }
}