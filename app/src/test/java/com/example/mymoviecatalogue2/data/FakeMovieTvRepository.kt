package com.example.mymoviecatalogue2.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mymoviecatalogue2.data.source.local.LocalDataSource
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.remote.ApiResponse
import com.example.mymoviecatalogue2.data.source.remote.NetworkBoundResource

import com.example.mymoviecatalogue2.data.source.remote.RemoteDataSource
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResults
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResults
import com.example.mymoviecatalogue2.utils.AppExecutors
import com.example.mymoviecatalogue2.vo.Resource

class FakeMovieTvRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTvDataSource {


    override fun getMoviePopular(): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<MovieResults>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getDataMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResults>>> =
                remoteDataSource.getListMovies()

            override fun saveCallResult(data: List<MovieResults>) {
                val listMovie = ArrayList<Movie>()
                for (dataMovie in data) {
                    dataMovie.apply {
                        val movie = Movie(
                            moviesId,
                            title,
                            overview,
                            releaseDate,
                            imagePath
                        )
                        listMovie.add(movie)
                    }
                }
                localDataSource.insertMovie(listMovie)
            }

        }.asLiveData()
    }

    override fun getTvPopular(): LiveData<Resource<PagedList<TvShow>>> {
        return object : NetworkBoundResource<PagedList<TvShow>, List<TvResults>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShow>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getDataTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvResults>>> =
                remoteDataSource.getListTvShow()

            override fun saveCallResult(data: List<TvResults>) {
                val listTv = ArrayList<TvShow>()
                for (dataTv in data) {
                    dataTv.apply {
                        val tv = TvShow(
                            tvId,
                            title,
                            overview,
                            firstAirDate,
                            imagePath
                        )
                        listTv.add(tv)
                    }
                }
                localDataSource.insertTv(listTv)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(idMovie: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, DetailResults>(appExecutors) {
            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<Movie> =
                localDataSource.getMovieId(idMovie)

            override fun createCall(): LiveData<ApiResponse<DetailResults>> =
                remoteDataSource.getDetailMovies(idMovie)

            override fun saveCallResult(data: DetailResults) {
                with(data) {
                    val detailMovie = Movie(
                        movieId = moviesId,
                        title = title,
                        overview = overview,
                        releaseDate = releaseDate,
                        imagePath = imagePath,
                        isFav = false
                    )
                    localDataSource.updateFavMovie(detailMovie, false)
                }
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(idTvShow: Int): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, DetailTvResults>(appExecutors) {
            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<TvShow> =
                localDataSource.getTvId(idTvShow)

            override fun createCall(): LiveData<ApiResponse<DetailTvResults>> =
                remoteDataSource.getDetailTvShows(idTvShow)

            override fun saveCallResult(data: DetailTvResults) {
                with(data) {
                    val detailTv = TvShow(
                        tvId = tvId,
                        title = title,
                        overview = overview,
                        firstAirDate = firstAirDate,
                        imagePath = imagePath
                    )
                    localDataSource.updateFavTv(detailTv, false)
                }
            }

        }.asLiveData()

    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateFavMovie(movie, state)
        }

    override fun setFavoriteTv(tv: TvShow, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateFavTv(tv, state)
        }

    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }
}