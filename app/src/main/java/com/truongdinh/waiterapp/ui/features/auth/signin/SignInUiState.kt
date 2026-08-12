package com.truongdinh.waiterapp.ui.features.auth.signin

data class SignInUiState(
    val username: String = "",
    val password: String = "",

    val usernameError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)