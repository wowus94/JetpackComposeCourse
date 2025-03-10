package ru.vlyashuk.jetpackcomposecourse.vk_app


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.StatisticItem

class NewsFeedViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(
                id = it,
                contentText = "$it"))
        }
    }

    private val initialState = FeedPostsScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<FeedPostsScreenState>(initialState)
    val screenState: LiveData<FeedPostsScreenState> = _screenState

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is FeedPostsScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = FeedPostsScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is FeedPostsScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = FeedPostsScreenState.Posts(posts = oldPosts)
    }
}