package com.glu.testwork.di

import androidx.lifecycle.ViewModel
import com.glu.testwork.presentation.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(GameViewModel::class)
    @Binds
    fun bindsGifsViewModel(impl: GameViewModel): ViewModel
}

