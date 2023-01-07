package com.glu.testwork.di

import com.glu.testwork.data.api.ApiFactory
import com.glu.testwork.data.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class GamesModuleProvides {

    @Provides
    fun providesApiService(): ApiService {
        return ApiFactory.create()
    }
}