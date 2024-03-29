package com.example.core.domain

import com.example.core.data.Resource
import com.example.core.repository.IfMovieRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InteractorMovie @Inject constructor(private val ifMovieRepo: IfMovieRepo) : UseCaseMovie{
    override fun allMovie(): Flow<Resource<List<Movie>>> {
        return ifMovieRepo.allMovie()
    }

    override fun movieFavorite(): Flow<List<Movie>> {
        return ifMovieRepo.movieFavorite()
    }

    override fun movieSearch(search: String): Flow<List<Movie>> {
        return ifMovieRepo.movieSearch(search)
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return ifMovieRepo.setMovieFavorite(movie, state)
    }

}