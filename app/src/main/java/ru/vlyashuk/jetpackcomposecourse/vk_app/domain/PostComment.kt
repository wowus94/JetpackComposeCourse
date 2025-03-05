package ru.vlyashuk.jetpackcomposecourse.vk_app.domain

import ru.vlyashuk.jetpackcomposecourse.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_avatar,
    val commentText: String = "Text comment",
    val publicDate: String = "11:11"
)
