package com.example.core.data

import android.annotation.SuppressLint
import com.example.core.data.local.DataLocal
import com.example.core.data.remote.response.MovieResponse
import com.example.core.data.remote.retrofit.ApiResponse
import com.example.core.domain.Movie
import com.example.core.repository.IfMovieRepo
import com.example.core.utils.AppExe
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val dataSource : DataSource,
    private val dataLocal: DataLocal,
    private val appExecutors: AppExe
) : IfMovieRepo{
    override fun allMovie(): Flow<Resource<List<Movie>>> {
      return object : Network<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return dataLocal.allMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

          override fun fetch(result: List<Movie>?): Boolean {
                return result.isNullOrEmpty()
          }

          @SuppressLint("SuspiciousIndentation")
          override suspend fun saveCall(data: List<MovieResponse>) {
              val dataMovie = DataMapper.mapMovieResponsesToEntities(data)
                dataLocal.insertMovie(dataMovie)
          }


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return dataSource.getMovies()
            }


        }.asFlow()
      }


    override fun movieFavorite(): Flow<List<Movie>> {
        return dataLocal.movieFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun movieSearch(search: String): Flow<List<Movie>> {
        return dataLocal.movieSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            dataLocal.setMovieFav(movieEntity, state)
        }
    }

}
