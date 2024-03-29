package com.example.submission_dicoding_expert.di

import com.example.core.domain.InteractorMovie
import com.example.core.domain.UseCaseMovie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideUseCaseMovie(interactorMovie: InteractorMovie): UseCaseMovie
}