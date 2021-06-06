package com.example.mymoviecatalogue2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviecatalogue2.data.MovieTvRepository
import com.example.mymoviecatalogue2.detail.DetailViewModel
import com.example.mymoviecatalogue2.di.Injection
import com.example.mymoviecatalogue2.favorite.FavoriteViewModel
import com.example.mymoviecatalogue2.movie.MovieViewModel
import com.example.mymoviecatalogue2.tvshow.TvShowViewModel

class ViewModelFactory(private val mMovieTvRepository: MovieTvRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieTvRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieTvRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieTvRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieTvRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}