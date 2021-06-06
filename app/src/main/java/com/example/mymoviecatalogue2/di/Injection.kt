package com.example.mymoviecatalogue2.di

import android.content.Context
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.data.source.local.LocalDataSource
import com.example.mymoviecatalogue2.data.source.local.room.MovieTvDatabase
import com.example.mymoviecatalogue2.data.source.remote.RemoteDataSource
import com.example.mymoviecatalogue2.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieTvRepository {

        val database = MovieTvDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getINSTANCE(database.catalogueDao())
        val appExecutors = AppExecutors()

        return MovieTvRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}