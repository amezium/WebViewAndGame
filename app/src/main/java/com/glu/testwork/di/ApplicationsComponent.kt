package com.glu.testwork.di

import com.glu.testwork.presentation.fragments.GameFragment
import com.glu.testwork.presentation.fragments.LoadFragment
import com.glu.testwork.presentation.fragments.RecordFragment
import com.glu.testwork.presentation.fragments.SettingFragment
import dagger.Component

@ApplicationScope
@Component(modules = [GamesModule::class, GamesModuleProvides::class, ViewModelModule::class])
interface ApplicationsComponent {

    fun inject(gameFragment: GameFragment)
    fun inject(recordFragment: RecordFragment)
    fun inject(settingsFragment: SettingFragment)
    fun inject(loadFragment: LoadFragment)
}