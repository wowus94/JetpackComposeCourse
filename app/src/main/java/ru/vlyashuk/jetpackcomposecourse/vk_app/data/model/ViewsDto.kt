package ru.vlyashuk.jetpackcomposecourse.vk_app.data.model

import com.google.gson.annotations.SerializedName

data class ViewsDto(
    @SerializedName("count") val count: Int
)
