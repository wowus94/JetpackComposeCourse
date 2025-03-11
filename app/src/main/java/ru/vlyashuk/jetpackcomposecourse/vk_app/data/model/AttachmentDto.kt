package ru.vlyashuk.jetpackcomposecourse.vk_app.data.model

import com.google.gson.annotations.SerializedName

data class AttachmentDto(
    @SerializedName("photo") val photo: PhotoDto?
)
