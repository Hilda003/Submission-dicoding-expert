package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.UseCaseMovie
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val useCaseMovie: UseCaseMovie) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(useCaseMovie) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}