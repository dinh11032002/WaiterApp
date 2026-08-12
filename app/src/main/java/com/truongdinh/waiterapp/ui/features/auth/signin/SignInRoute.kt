package com.truongdinh.waiterapp.ui.features.auth.signin

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignInRoute(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    SignInScreen(
        state = state,
        onPasswordChange = viewModel::onPasswordChanged,
        onUsernameChange = viewModel::onUsernameChanged,
        onSignInClick = {
            viewModel.signIn(
                onSuccess = {
                    navController.navigate("home")
                }
            )
        }
    )
}