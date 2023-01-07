package com.glu.testwork.presentation

import androidx.lifecycle.ViewModel
import com.glu.testwork.data.api.dto.Container
import com.glu.testwork.domain.SendDataUseCase
import javax.inject.Inject

class GameViewModel @Inject constructor(private val sendDataUseCase: SendDataUseCase): ViewModel() {

    //значения для фрагмента с рекордом и сложностью
    var firstPlace = 0
    var secondPlace = 0
    var thirdPlace= 0
    var hardLevel = false



    /**
     * Отправляю данные на сервер(нужно их передать в конструктор)
     */
    suspend fun getData(): Container {
        return sendDataUseCase()
    }
}