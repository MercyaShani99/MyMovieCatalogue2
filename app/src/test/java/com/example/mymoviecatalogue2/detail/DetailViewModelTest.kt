package com.example.mymoviecatalogue2.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.utils.DataDummy
import com.example.mymoviecatalogue2.utils.DetailDummy
import com.example.mymoviecatalogue2.vo.Resource
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovies = DataDummy.generateDummyMovie()[0]
    private val movId = dummyMovies.movieId
    private val dummyTvShows = DataDummy.generateDummyTvShow()[0]
    private val tvsId = dummyTvShows.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var movieTvRepository = mock(MovieTvRepository::class.java)

    @Mock
    private lateinit var movieObserver: Observer<Resource<Movie>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvShow>>

    @Before
    fun setUpM() {
        detailViewModel = DetailViewModel(movieTvRepository)
    }

    @Test
    fun getDetailMovie() {
        movId?.let { detailViewModel.setDetailMovie(it) }

        val moviesDetail = Resource.success(DetailDummy.getDetailMovie(movId))
        val course = MutableLiveData<Resource<Movie>>()
        course.value = moviesDetail

        `when`(movId?.let { movieTvRepository.getDetailMovie(it) }).thenReturn(course)
        detailViewModel.getDetailMovie.observeForever(movieObserver)
        verify(movieTvRepository).getDetailMovie(movId!!)
        assertNotNull(detailViewModel.getDetailMovie)
        assertEquals(detailViewModel.getDetailMovie.value?.data?.movieId, dummyMovies.movieId)
    }

    @Test
    fun setFavoriteMovie() {
        movId?.let { detailViewModel.setDetailMovie(it) }

        val favMovDetail = Resource.success(DataDummy.generateDummyMovie()[0])
        val favm = MutableLiveData<Resource<Movie>>()
        favm.value = favMovDetail

        `when`(movId?.let { movieTvRepository.getDetailMovie(it) }).thenReturn(favm)
        detailViewModel.setFavoriteMovie()

        detailViewModel.getDetailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(favm.value)

        val expectedValue = favm.value
        val actualValue = detailViewModel.getDetailMovie.value

        TestCase.assertEquals(expectedValue, actualValue)
    }

    @Before
    fun setUpT() {
        detailViewModel = DetailViewModel(movieTvRepository)
    }

    @Test
    fun getDetailTv() {
        tvsId?.let { detailViewModel.setDetailTv(it) }

        val tvsDetail = Resource.success(DetailDummy.getDetailTv(tvsId))
        val course = MutableLiveData<Resource<TvShow>>()
        course.value = tvsDetail

        `when`(tvsId?.let { movieTvRepository.getDetailTvShow(it) }).thenReturn(course)
        detailViewModel.getDetailTvShow.observeForever(tvObserver)
        verify(movieTvRepository).getDetailTvShow(tvsId!!)
        assertNotNull(detailViewModel.getDetailTvShow)
        assertEquals(detailViewModel.getDetailTvShow.value?.data?.tvId, dummyTvShows.tvId)
    }

    @Test
    fun setFavoriteTv() {
        tvsId?.let { detailViewModel.setDetailTv(it) }

        val favTvDetail = Resource.success(DataDummy.generateDummyTvShow()[0])
        val favt = MutableLiveData<Resource<TvShow>>()
        favt.value = favTvDetail

        `when`(tvsId?.let { movieTvRepository.getDetailTvShow(it) }).thenReturn(favt)
        detailViewModel.setFavoriteTv()

        detailViewModel.getDetailTvShow.observeForever(tvObserver)
        verify(tvObserver).onChanged(favt.value)

        val expectedValue = favt.value
        val actualValue = detailViewModel.getDetailTvShow.value

        TestCase.assertEquals(expectedValue, actualValue)
    }
}