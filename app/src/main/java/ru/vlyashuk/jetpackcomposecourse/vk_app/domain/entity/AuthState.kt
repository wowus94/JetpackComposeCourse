package ru.vlyashuk.jetpackcomposecourse.vk_app.domain.entity

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()

}