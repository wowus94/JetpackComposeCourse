package ru.vlyashuk.jetpackcomposecourse

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import ru.vlyashuk.jetpackcomposecourse.domain.FeedPost
import ru.vlyashuk.jetpackcomposecourse.domain.StatisticItem
import ru.vlyashuk.jetpackcomposecourse.domain.StatisticType

@Composable
fun VkPostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onStatisticItemClickListener: (StatisticItem) -> Unit
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
            modifier = Modifier.padding(8.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(start = 8.dp, end = 8.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = feedPost.contentImageResId), contentDescription = ""
        )
        Spacer(modifier = Modifier.height(8.dp))
        PostBottomBar(
            statistics = feedPost.statistics,
            onItemClickListener = onStatisticItemClickListener
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
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = feedPost.avatarResId), contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = Color.Gray
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
    onItemClickListener: (StatisticItem) -> Unit
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
                    onItemClickListener(viewsItem)
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
                    onItemClickListener(sharesItem)
                })
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            TextIcon(
                text = commentsItem.count.toString(),
                iconId = R.drawable.ic_comment,
                onItemClickListener = {
                    onItemClickListener(commentsItem)
                })
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            TextIcon(
                text = likesItem.count.toString(),
                iconId = R.drawable.ic_like,
                onItemClickListener = {
                    onItemClickListener(likesItem)
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