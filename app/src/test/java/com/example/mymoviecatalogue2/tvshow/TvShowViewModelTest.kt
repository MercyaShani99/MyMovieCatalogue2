package com.example.mymoviecatalogue2.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvRepository)
    }

    @Test
    fun getTvShow() {

        val dummyTv = DataDummy.generateDummyTvShow()
        val listTv = MutableLiveData<List<TvShow>>()
        listTv.value = dummyTv

        `when`(tvRepository.loadAllTvShow()).thenReturn(listTv)
        val tvShowEntity = viewModel.getTvShow().value
        verify(tvRepository).loadAllTvShow()
        assertEquals(10, tvShowEntity?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}