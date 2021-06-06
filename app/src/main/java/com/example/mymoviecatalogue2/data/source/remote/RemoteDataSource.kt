package com.example.mymoviecatalogue2.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymoviecatalogue2.data.source.remote.api.ApiConfig
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResponse
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResults
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResponse
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResults
import com.example.mymoviecatalogue2.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getListMovies(): LiveData<ApiResponse<List<MovieResults>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieResults>>>()
        ApiConfig.getApiService().getMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ){
                    Log.d(this@RemoteDataSource.toString(), "Get Movie Succeed")
                    result.value = ApiResponse.success(response.body()?.results as List<MovieResults>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(this@RemoteDataSource.toString(), "Get Movie Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

    fun getListTvShow(): LiveData<ApiResponse<List<TvResults>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvResults>>>()
        ApiConfig.getApiService().getTv()
            .enqueue(object : Callback<TvResponse> {
                override fun onResponse(
                    call: Call<TvResponse>,
                    response: Response<TvResponse>
                ){
                    Log.d(this@RemoteDataSource.toString(), "Get Movie Succeed")
                    result.value = ApiResponse.success(response.body()?.results as List<TvResults>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.e(this@RemoteDataSource.toString(), "Get Movie Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

    fun getDetailMovies(id: Int): LiveData<ApiResponse<DetailResults>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<DetailResults>>()
        ApiConfig.getApiService().getDetailMovie(id)
            .enqueue(object : Callback<DetailResults> {
                override fun onResponse(
                    call: Call<DetailResults>,
                    response: Response<DetailResults>
                ){
                    Log.d(this@RemoteDataSource.toString(), "Get Movie Succeed")
                    result.value = ApiResponse.success(response.body() as DetailResults)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailResults>, t: Throwable) {
                    Log.e(this@RemoteDataSource.toString(), "Get Movie Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

    fun getDetailTvShows(id: Int): LiveData<ApiResponse<DetailTvResults>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<DetailTvResults>>()
        ApiConfig.getApiService().getDetailTv(id)
            .enqueue(object : Callback<DetailTvResults> {
                override fun onResponse(
                    call: Call<DetailTvResults>,
                    response: Response<DetailTvResults>
                ){
                    Log.d(this@RemoteDataSource.toString(), "Get Movie Succeed")
                    result.value = ApiResponse.success(response.body() as DetailTvResults)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailTvResults>, t: Throwable) {
                    Log.e(this@RemoteDataSource.toString(), "Get Movie Failed ${t.message}")
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }


}