package com.example.core.data

import com.example.core.data.remote.retrofit.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class Network<Result, Request> {


    private var result: Flow<Resource<Result>> = flow {
            emit(Resource.Loading())
            val dbSource = loadFromDB().first()
            if (fetch(dbSource)) {
               emit(Resource.Loading())
                when (val apiResponse = createCall().first()) {
                   is ApiResponse.Success -> {
                       saveCall(apiResponse.data)
                       emitAll(loadFromDB().map {
                           Resource.Success(
                               it
                           )
                       })
                   }
                   is ApiResponse.Empty -> {
                       emitAll(loadFromDB().map {
                           Resource.Success(
                               it
                           )
                       })
                   }
                   is ApiResponse.Error -> {
                       onFetchFailed()
                       emit(
                           Resource.Error(
                               apiResponse.errorMessage
                           )
                       )
                   }
               }

            }
            else {
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
        }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<Request>>
   protected abstract fun loadFromDB(): Flow<Result>

    fun asFlow(): Flow<Resource<Result>> = result
    protected abstract suspend fun saveCall(data: Request)

    protected abstract fun fetch(result: Result?): Boolean

}