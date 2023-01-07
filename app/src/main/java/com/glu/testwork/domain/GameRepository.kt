package com.glu.testwork.domain

import com.glu.testwork.data.api.dto.Container


interface GameRepository {

    /**
     * Отправляю данные на сервер
     */
    suspend fun getData(): Container
}