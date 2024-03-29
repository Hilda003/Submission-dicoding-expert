package com.example.core.di

import com.example.core.data.MovieRepo
import com.example.core.repository.IfMovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DBModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideRepository(repo: MovieRepo): IfMovieRepo
}