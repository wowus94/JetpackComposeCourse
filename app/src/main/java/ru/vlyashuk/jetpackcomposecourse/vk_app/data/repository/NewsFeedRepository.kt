package ru.vlyashuk.jetpackcomposecourse.vk_app.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import ru.vlyashuk.jetpackcomposecourse.vk_app.data.mapper.NewsFeedMapper
import ru.vlyashuk.jetpackcomposecourse.vk_app.data.network.ApiFactory
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.PostComment
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.StatisticItem
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.StatisticType

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    suspend fun loadRecommendations(): List<FeedPost> {
        val startFrom = nextFrom

        if (startFrom == null && feedPosts.isNotEmpty()) return feedPosts
        val response = if (startFrom == null) {
            apiService.loadRecommendations(getAccessToken())
        } else {
            apiService.loadRecommendations(getAccessToken(), startFrom)
        }
        nextFrom = response.newsFeedContent.nextFrom
        val posts = mapper.mapResponseToPosts(response)
        _feedPosts.addAll(posts)
        return feedPosts
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    suspend fun getComments(feedPost: FeedPost): List<PostComment> {
        val comments = apiService.getComments(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        return mapper.mapResponseToComments(comments)
    }

    suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost)
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }

        val newLikesCount = response.likes.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
    }
}