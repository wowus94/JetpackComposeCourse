package ru.vlyashuk.jetpackcomposecourse.vk_app.presentation.main

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()

}