package com.glu.testwork.di

import com.glu.testwork.data.repository.GameRepositoryImpl
import com.glu.testwork.domain.GameRepository
import dagger.Binds
import dagger.Module

@Module
interface GamesModule {

    @ApplicationScope
    @Binds
    fun bindsRepository(impl: GameRepositoryImpl): GameRepository
}