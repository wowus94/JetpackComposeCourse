package ru.vlyashuk.jetpackcomposecourse.vk_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}