package ru.vlyashuk.jetpackcomposecourse.vk_app.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedContentDto(
    @SerializedName("items") val posts: List<PostDto>,
    @SerializedName("groups") val groups: List<GroupDto>
)
