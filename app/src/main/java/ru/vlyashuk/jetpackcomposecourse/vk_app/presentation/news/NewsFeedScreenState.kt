package ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.news

import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost

sealed class NewsFeedScreenState {

    data object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()

}