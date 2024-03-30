package com.example.submission_dicoding_expert.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.domain.Movie
import com.example.core.domain.UseCaseMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCaseMovie: UseCaseMovie) : ViewModel(){

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        useCaseMovie.setMovieFavorite(movie, newStatus)
}