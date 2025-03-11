package ru.vlyashuk.jetpackcomposecourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import ru.vlyashuk.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import ru.vlyashuk.jetpackcomposecourse.vk_app.AuthState
import ru.vlyashuk.jetpackcomposecourse.vk_app.LoginViewModel
import ru.vlyashuk.jetpackcomposecourse.vk_app.VkMainScreen
import ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.LoginScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                val viewModel: LoginViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)


                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult(it)
                }

                when (authState.value) {
                    is AuthState.Authorized -> {
                        VkMainScreen()
                    }

                    is AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL))
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}

