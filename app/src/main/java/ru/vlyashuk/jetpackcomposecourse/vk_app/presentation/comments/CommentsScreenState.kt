package ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.comments

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity.PostComment

sealed class CommentsScreenState {

    data object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()

}