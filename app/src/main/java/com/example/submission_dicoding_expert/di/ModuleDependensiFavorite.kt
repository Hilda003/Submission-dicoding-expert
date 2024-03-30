package com.example.submission_dicoding_expert.di

import com.example.core.domain.UseCaseMovie
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface ModuleDependensiFavorite {
    fun useCaseMovie() : UseCaseMovie
}