package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.repository.NewsFeedRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}