package com.example.favorite.di

import android.content.Context

import com.example.favorite.FavoriteFragment
import com.example.submission_dicoding_expert.di.ModuleDependensiFavorite
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ModuleDependensiFavorite::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDepedencies: ModuleDependensiFavorite): Builder
        fun build(): FavoriteComponent
    }

}