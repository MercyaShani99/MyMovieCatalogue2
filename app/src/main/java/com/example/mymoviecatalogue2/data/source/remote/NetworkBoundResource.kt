package com.example.mymoviecatalogue2.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mymoviecatalogue2.utils.AppExecutors
import com.example.mymoviecatalogue2.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("Leaking This")
        val databaseSource = loadFromDb()

        result.addSource(databaseSource) { data ->
            result.removeSource(databaseSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(databaseSource)
            } else {
                result.addSource(databaseSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }

        }
    }


    private fun onFetchFailed(){}

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.status) {
                StatusResponse.SUCCESS ->
                    appExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> appExecutors.mainThread().execute {
                    result.addSource(loadFromDb()) { newData ->
                        result.value = Resource.success(newData)

                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }

        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}