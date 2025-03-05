package ru.vlyashuk.jetpackcomposecourse.vk_app.domain

import ru.vlyashuk.jetpackcomposecourse.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "Подслушано у программистов",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.image_avatar,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae tortor sed magna auctor pretium a id risus. Praesent ut tortor tincidunt leo dictum sollicitudin vel non justo.",
    val contentImageResId: Int = R.drawable.image_post_content,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 966),
        StatisticItem(type = StatisticType.SHARES, 7),
        StatisticItem(type = StatisticType.COMMENTS, 8),
        StatisticItem(type = StatisticType.LIKES, 27)
    )
)
