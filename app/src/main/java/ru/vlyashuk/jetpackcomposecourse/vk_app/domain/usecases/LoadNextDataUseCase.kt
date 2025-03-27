package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}