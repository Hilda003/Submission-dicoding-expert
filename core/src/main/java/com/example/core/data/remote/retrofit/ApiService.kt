package com.example.core.data.remote.retrofit

import com.example.core.data.remote.response.ListMovie
import com.example.core.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getListMovie(
        @Query("api_key") apiKey : String
    ) : ListMovie
}