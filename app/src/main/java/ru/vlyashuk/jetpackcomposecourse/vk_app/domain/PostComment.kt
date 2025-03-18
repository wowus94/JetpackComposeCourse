package ru.vlyashuk.jetpackcomposecourse.vk_app.domain

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicDate: String
)
