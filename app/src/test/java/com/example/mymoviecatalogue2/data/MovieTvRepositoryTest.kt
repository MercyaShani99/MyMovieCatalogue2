package com.example.mymoviecatalogue2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mymoviecatalogue2.data.source.remote.RemoteDataSource
import com.example.mymoviecatalogue2.utils.DataDummy
import com.example.mymoviecatalogue2.utils.DetailDummy
import com.example.mymoviecatalogue2.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieTvRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeMovieTvRepository(remote)

    private val movieResponse = DataDummy.getRemoteMovie()
    private val tvResponse = DataDummy.getRemoteTvShow()


    private val movieId = movieResponse[0].moviesId.toString()
    private val tvId = tvResponse[0].tvId.toString()

    private val movieDetail = DetailDummy.getRemoteDetailMovie()
    private val tvDetail = DetailDummy.getRemoteDetailTvShow()

    private val movieGenre = DataDummy.listGenreMovie()
    private val tvGenre = DataDummy.listGenreMovie()

    @Test
    fun loadMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback).onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getListMovies(any())

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadGenreCallback).onAllGenreMovieReceived(movieGenre)
            null
        }.`when`(remote).getMovieGenres(any())

        val movieEntity = LiveDataTestUtil.getValue(catalogueRepository.loadAllMovies())
        verify(remote).getListMovies(any())
        verify(remote).getMovieGenres(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun loadDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onAllDetailMoviesReceived(movieDetail)
            null
        }.`when`(remote).getDetailMovies(any(), eq(movieId))

        val movieDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.loadDetailMovie(movieId))
        verify(remote).getDetailMovies(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.moviesId, movieDetailEntity.movieId)
    }

    @Test
    fun loadTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowsReceived(tvResponse)
            null
        }.`when`(remote).getListTvShow(any())

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadGenreCallback).onAllGenreMovieReceived(tvGenre)
            null
        }.`when`(remote).getTvGenres(any())

        val tvEntity = LiveDataTestUtil.getValue(catalogueRepository.loadAllTvShow())

        verify(remote).getListTvShow(any())
        verify(remote).getTvGenres(any())
        assertNotNull(tvEntity)
        assertEquals(movieResponse.size.toLong(), tvEntity.size.toLong())
    }

    @Test
    fun loadDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onAllDetailTvShowsReceived(tvDetail)
            null
        }.`when`(remote).getDetailTvShows(any(), eq(tvId))

        val tvDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.loadDetailTvShow(tvId))
        verify(remote).getDetailTvShows(any(), eq(tvId))
        assertNotNull(tvDetailEntity)
        assertEquals(tvDetail.moviesId, tvDetailEntity.tvId)
    }
}