package ru.vlyashuk.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vlyashuk.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import ru.vlyashuk.jetpackcomposecourse.vk_app.VkMainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                VkMainScreen()
            }
        }
    }
}

