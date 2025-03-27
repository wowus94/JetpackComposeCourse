package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.AuthState
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.repository.NewsFeedRepository
import javax.inject.Inject

class GetAuthStateFlowUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}