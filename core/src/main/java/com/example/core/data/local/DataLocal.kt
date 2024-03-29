package com.example.core.data.local

import com.example.core.data.local.room.DaoMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataLocal @Inject constructor(private val daoMovie: DaoMovie){

    fun allMovie() : Flow<List<EntityMovie>> = daoMovie.allMovie()

    fun movieFavorite()  : Flow<List<EntityMovie>> = daoMovie.movieFavorite()

    fun movieSearch(search: String)  : Flow<List<EntityMovie>> = daoMovie.movieSearch(search)
        .flowOn(Dispatchers.Default)
        .conflate()

    suspend fun insertMovie(entityMovie: List<EntityMovie>) = daoMovie.insertMovie(entityMovie)

    fun setMovieFav(entityMovie: EntityMovie, newState : Boolean) {
        entityMovie.isFavorite = newState
        daoMovie.updateFavoriteMovie(entityMovie)
    }

}