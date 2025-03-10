package ru.vlyashuk.jetpackcomposecourse.vk_app

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.PostComment

sealed class FeedPostsScreenState {

    data object Initial : FeedPostsScreenState()

    data class Posts(val posts: List<FeedPost>) : FeedPostsScreenState()

}