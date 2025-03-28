package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.PostComment
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.repository.NewsFeedRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}