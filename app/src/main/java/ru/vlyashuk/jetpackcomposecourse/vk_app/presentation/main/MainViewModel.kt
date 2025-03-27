package ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vlyashuk.jetpackcomposecourse.vk_app.data.repository.NewsFeedRepositoryImpl
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases.CheckAuthUseCase
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases.GetAuthStateFlowUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthUseCase(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }

}