package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.UseCaseMovie
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(useCaseMovie: UseCaseMovie) : ViewModel(){
    val favoriteMovie = useCaseMovie.movieFavorite().asLiveData()
}