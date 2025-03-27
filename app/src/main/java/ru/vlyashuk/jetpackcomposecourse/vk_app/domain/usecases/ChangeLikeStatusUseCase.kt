package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.repository.NewsFeedRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}