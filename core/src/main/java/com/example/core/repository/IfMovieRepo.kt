package com.example.core.repository

import com.example.core.data.Resource
import com.example.core.domain.Movie
import kotlinx.coroutines.flow.Flow

interface IfMovieRepo {
    fun allMovie()  : Flow<Resource<List<Movie>>>

    fun movieFavorite() : Flow<List<Movie>>

    fun movieSearch(search: String) : Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}