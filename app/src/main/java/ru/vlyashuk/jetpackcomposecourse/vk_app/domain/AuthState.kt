package ru.vlyashuk.jetpackcomposecourse.vk_app.domain

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()

}