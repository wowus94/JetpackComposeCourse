package ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.vlyashuk.jetpackcomposecourse.R
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.FeedPost
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.StatisticItem
import ru.vlyashuk.jetpackcomposecourse.vk_app.domain.StatisticType

@Composable
fun VkPostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(0.5.dp, Color.Gray),
        shape = RoundedCornerShape(4.dp)
    ) {
        PostTopBar(
            feedPost = feedPost
        )
        Text(
            text = feedPost.contentText,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(8.dp)
        )
        AsyncImage(
            model = feedPost.contentImageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 8.dp, end = 8.dp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(8.dp))
        PostBottomBar(
            statistics = feedPost.statistics,
            onViewsClickListener = onViewsClickListener,
            onShareClickListener = onShareClickListener,
            onCommentClickListener = onCommentClickListener,
            onLikeClickListener = onLikeClickListener
        )
    }
}

@Composable
private fun PostTopBar(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Inside,
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = Icons.Default.MoreVert, contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PostBottomBar(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
        Row(modifier = Modifier.weight(1f)) {
            TextIcon(
                text = viewsItem.count.toString(),
                iconId = R.drawable.ic_views_count,
                onItemClickListener = {
                    onViewsClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            TextIcon(
                text = sharesItem.count.toString(),
                iconId = R.drawable.ic_share,
                onItemClickListener = {
                    onShareClickListener(sharesItem)
                })
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            TextIcon(
                text = commentsItem.count.toString(),
                iconId = R.drawable.ic_comment,
                onItemClickListener = {
                    onCommentClickListener(commentsItem)
                })
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            TextIcon(
                text = likesItem.count.toString(),
                iconId = R.drawable.ic_like,
                onItemClickListener = {
                    onLikeClickListener(likesItem)
                })
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException("Type unknown")
}

@Composable
private fun TextIcon(
    text: String,
    iconId: Int,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}