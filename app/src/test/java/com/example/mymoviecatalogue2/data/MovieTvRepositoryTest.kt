package com.example.mymoviecatalogue2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mymoviecatalogue2.data.source.local.LocalDataSource
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.remote.RemoteDataSource
import com.example.mymoviecatalogue2.utils.*
import com.example.mymoviecatalogue2.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTvRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieTvRepository = FakeMovieTvRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateDummyMovie()
    private val movId = movieResponse[0].movieId
    private val tvResponse = DataDummy.generateDummyTvShow()
    private val tvsId = tvResponse[0].tvId

    @Test
    fun getMoviePopular() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getDataMovie()).thenReturn(dataSourceFactory)
        movieTvRepository.getMoviePopular()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getDataMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyEntity = MutableLiveData<Movie>()
        dummyEntity.value = DetailDummy.getDetailMovie(DataDummy.generateDummyMovie()[0].movieId)
        `when`(movId?.let { local.getMovieId(it) }).thenReturn(dummyEntity)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieTvRepository.getDetailMovie(movId!!))
        verify(local).getMovieId(movId)
        assertNotNull(movieDetailEntity.data)
        assertNotNull(movieDetailEntity.data?.title)
    }

    @Test
    fun getTvPopular() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
        `when`(local.getDataTv()).thenReturn(dataSourceFactory)
        movieTvRepository.getTvPopular()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getDataTv()
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getTvDetail() {
        val dummyEntity = MutableLiveData<TvShow>()
        dummyEntity.value = DetailDummy.getDetailTv(DataDummy.generateDummyTvShow()[0].tvId)
        `when`(tvsId?.let { local.getTvId(it) }).thenReturn(dummyEntity)

        val tvDetailEntities = LiveDataTestUtil.getValue(movieTvRepository.getDetailTvShow(tvsId!!))
        verify(local).getTvId(tvsId)
        assertNotNull(tvDetailEntities)
        assertNotNull(tvDetailEntities.data?.title)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getFavMovie()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteMovie()

        val favMovEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getFavMovie()
        assertNotNull(favMovEntities)
        assertEquals(movieResponse.size.toLong(), favMovEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
        `when`(local.getFavTv()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteTv()

        val favTvEntities = Resource.success((PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow())))
        verify(local).getFavTv()
        assertNotNull(favTvEntities)
        assertEquals(tvResponse.size.toLong(), favTvEntities.data?.size?.toLong())
    }
}