package com.glu.testwork.data.repository

import com.glu.testwork.data.api.ApiService
import com.glu.testwork.data.api.dto.Container
import com.glu.testwork.domain.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val apiService: ApiService): GameRepository {

    /**
     * Отправляю данные на сервер
     */
    override suspend fun getData(): Container {
        return apiService.getDataServer()
    }
}