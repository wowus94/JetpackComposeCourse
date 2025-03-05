package ru.vlyashuk.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vlyashuk.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<VkViewModel>()
    private val viewModelInst by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestLazyColumn()
        }
    }

    @Composable
    private fun TestLazyColumn() {
        JetpackComposeCourseTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                val models = viewModelInst.models.observeAsState(listOf())
                LazyColumn {
                    items(models.value, key = { it.id }) { model ->

                        val dismissState = rememberSwipeToDismissBoxState(
                            positionalThreshold = { it * 0.5f },
                            confirmValueChange = { value ->
                                val isDismissed = value in setOf(
                                    SwipeToDismissBoxValue.EndToStart
                                )
                                if (isDismissed) {
                                    viewModelInst.delete(model)
                                }
                                return@rememberSwipeToDismissBoxState isDismissed
                            })

                        SwipeToDismissBox(state = dismissState,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                        .background(color = Color.Red.copy(alpha = 0.5f)),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "Delete item",
                                        color = Color.White,
                                        fontSize = 24.sp
                                    )
                                }
                            }) {
                            ProfileCard(model = model,
                                onFollowedButtonClickListeners = {
                                    viewModelInst.changeFollowingStatus(model)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

