package ru.vlyashuk.jetpackcomposecourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import ru.vlyashuk.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import ru.vlyashuk.jetpackcomposecourse.vk_app.ActivityResultTest
import ru.vlyashuk.jetpackcomposecourse.vk_app.VkMainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    when (it) {
                        is VKAuthenticationResult.Success -> {
                            Log.i("MainActivity", "Success auth")
                        }

                        is VKAuthenticationResult.Failed -> {
                            Log.i("MainActivity", "Failed auth")
                        }
                    }
                }
                launcher.launch(listOf(VKScope.WALL))
                VkMainScreen()
            }
        }
    }
}

