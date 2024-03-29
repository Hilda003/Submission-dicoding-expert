package com.example.core.domain


import com.example.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface UseCaseMovie {

    fun allMovie() : Flow<Resource<List<Movie>>>

    fun movieFavorite() : Flow<List<Movie>>

    fun movieSearch(search: String) : Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}