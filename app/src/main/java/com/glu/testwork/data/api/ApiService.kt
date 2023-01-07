package com.glu.testwork.data.api

import com.glu.testwork.data.api.dto.Container
import retrofit2.http.GET


interface ApiService {

    @GET("plitka.json")
    suspend fun getDataServer(): Container
}