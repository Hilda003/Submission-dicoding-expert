package com.example.core.utils

import com.example.core.data.local.EntityMovie
import com.example.core.data.remote.response.MovieResponse
import com.example.core.domain.Movie

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<EntityMovie> {
        val dataMovie = ArrayList<EntityMovie>()
        input.map {
            val movie = EntityMovie(
                it.id,
                it.title,
                it.voteCount,
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                isFavorite = false,
            )
            dataMovie.add(movie)
        }
        return dataMovie
    }

    fun mapEntitiesToDomain(input: List<EntityMovie>): List<Movie> = input.map {
            Movie(
                it.id,
                it.title,
                it.voteCount,
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                isFavorite = it.isFavorite,
            )
        }


    fun mapDomainToEntity(input: Movie): EntityMovie = EntityMovie(
            input.id,
            input.title,
            input.voteCount,
            input.posterPath,
            input.backdropPath,
            input.overview,
            input.originalLanguage,
            input.releaseDate,
            input.popularity,
            input.voteAverage,
            isFavorite = input.isFavorite        )

}