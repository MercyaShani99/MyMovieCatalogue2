package com.example.mymoviecatalogue2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow

import com.example.mymoviecatalogue2.data.source.remote.RemoteDataSource
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResults
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResults

class FakeMovieTvRepository (private val remoteDataSource: RemoteDataSource) : MovieTvDataSource {


    override fun loadAllMovies(): LiveData<List<Movie>> {
        val getMovie  = MutableLiveData<List<Movie>>()
        remoteDataSource.getMovieGenres(object : RemoteDataSource.LoadGenreCallback {

            override fun onAllGenreMovieReceived(movieGenre: List<MovieGenres>?) {
                if (movieGenre != null) {
                    remoteDataSource.getListMovies(object : RemoteDataSource.LoadMovieCallback {
                        override fun onAllMoviesReceived(movieResponse: List<MovieResults>?) {
                            val listMovie = ArrayList<Movie>()
                            if (movieResponse != null) {
                                for(movie in movieResponse){
                                    movie.apply {
                                        val listGenre = ArrayList<String>()
                                        for (genre in genre) {
                                            for (genreName in movieGenre) {
                                                if (genreName.id == genre) {
                                                    listGenre.add(genreName.name)
                                                }
                                            }
                                        }
                                        val movies = Movie(
                                            moviesId,
                                            title,
                                            listGenre,
                                            overview,
                                            imagePath
                                        )

                                        listMovie.add(movies)
                                    }
                                }
                                getMovie.postValue(listMovie)
                            }

                        }
                    })
                }

            }

        })
        return getMovie
    }

    override fun loadAllTvShow(): LiveData<List<TvShow>> {
        val getTvShow = MutableLiveData<List<TvShow>>()
        remoteDataSource.getTvGenres(object : RemoteDataSource.LoadGenreCallback {

            override fun onAllGenreMovieReceived(movieGenre: List<MovieGenres>?) {
                if (movieGenre != null) {
                    remoteDataSource.getListTvShow(object : RemoteDataSource.LoadTvShowCallback {
                        override fun onAllTvShowsReceived(tvShowResponse: List<TvResults>?) {
                            val listTv = ArrayList<TvShow>()
                            if (tvShowResponse != null) {
                                for(tv in tvShowResponse){
                                    tv.apply {

                                        val listGenre = ArrayList<String>()
                                        for (genre in genre) {
                                            for (genreName in movieGenre) {
                                                if (genreName.id == genre) {
                                                    listGenre.add(genreName.name)
                                                }
                                            }
                                        }
                                        val tvShow = TvShow(
                                            tvId,
                                            title,
                                            listGenre,
                                            overview,
                                            imagePath
                                        )
                                        listTv.add(tvShow)
                                    }
                                }
                                getTvShow.postValue(listTv)
                            }
                        }
                    })
                }

            }
        })
        return getTvShow
    }

    override fun loadDetailMovie(movieId: String): LiveData<Movie> {
        val getDetailMovie = MutableLiveData<Movie>()
        remoteDataSource.getDetailMovies(object : RemoteDataSource.LoadDetailMovieCallback {

            override fun onAllDetailMoviesReceived(moviesDetail: DetailResults?) {
                lateinit var detailMovie : Movie
                moviesDetail?.apply {

                    val listGenre = ArrayList<String>()
                    for (genre in genre) {
                        listGenre.add(genre.name)
                    }

                    detailMovie = Movie(
                        movieId = moviesId,
                        title = title,
                        genre = listGenre,
                        overview = overview,
                        imagePath = imagePath
                    )
                    getDetailMovie.postValue(detailMovie)
                }
            }
        }, movieId)
        return getDetailMovie
    }

    override fun loadDetailTvShow(tvShowId: String): LiveData<TvShow> {
        val getDetailTv = MutableLiveData<TvShow>()
        remoteDataSource.getDetailTvShows(object : RemoteDataSource.LoadDetailTvShowCallback {

            override fun onAllDetailTvShowsReceived(tvShowsDetail: DetailTvResults?) {
                lateinit var detailTv : TvShow
                tvShowsDetail?.apply {

                    val listGenre = ArrayList<String>()
                    for (genre in genre) {
                        listGenre.add(genre.toString())
                    }

                    detailTv = TvShow(
                        tvId = moviesId,
                        title = title,
                        genre = listGenre,
                        overview = overview,
                        imagePath = imagePath
                    )
                    getDetailTv.postValue(detailTv)
                }
            }
        }, tvShowId)
        return getDetailTv
    }




}