package com.example.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.core.data.local.EntityMovie
import kotlinx.coroutines.flow.Flow
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoMovie {

    @Query("SELECT * FROM movie_table")
    fun allMovie() : Flow<List<EntityMovie>>

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1")
    fun movieFavorite() : Flow<List<EntityMovie>>

    @Query("SELECT * FROM movie_table WHERE isFavorite = 0 AND title LIKE '%' || :search || '%'")
    fun movieSearch(search: String) : Flow<List<EntityMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entityMovie: List<EntityMovie>)
    @Update
    fun updateFavoriteMovie(entityMovie: EntityMovie)
}