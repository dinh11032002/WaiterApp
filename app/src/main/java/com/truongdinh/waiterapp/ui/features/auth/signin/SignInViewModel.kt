package com.truongdinh.waiterapp.ui.features.auth.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdinh.waiterapp.data.local.session.SessionManager
import com.truongdinh.waiterapp.data.local.session.UserSession
import com.truongdinh.waiterapp.data.repository.StaffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: StaffRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    companion object {
        private const val TAG = "SignInViewModel"
    }

    var uiState by mutableStateOf(SignInUiState())
        private set

    fun onUsernameChanged(username: String) {
        Log.d(TAG, "Username changed: $username")

        uiState = uiState.copy(
            username = username
        )
    }

    fun onPasswordChanged(password: String) {
        Log.d(TAG, "Password changed")

        uiState = uiState.copy(
            password = password
        )
    }

    fun signIn(onSuccess: () -> Unit) {
        Log.d(TAG, "Sign in started")
        if (!validate()) return

        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                errorMessage = null
            )

            Log.d(
                TAG,
                "Checking account: ${uiState.username}"
            )

            repository.signIn(
                username = uiState.username,
                password = uiState.password,
            ).onSuccess { staff ->
                Log.d(
                    TAG,
                    "Login success: ${staff.fullName}"
                )

                sessionManager.saveSession(
                    UserSession(
                        staffId = staff.id,
                        staffName = staff.fullName,
                        shift = staff.shift,
                        isLoggedIn = true
                    )
                )

                Log.d(
                    TAG,
                    "Session saved for: ${staff.fullName}"
                )

                uiState = uiState.copy(
                    isLoading = false
                )

                Log.d(TAG, "Navigate to Home")

                onSuccess()
            }.onFailure {
                Log.e(
                    TAG,
                    "Login failed: ${it.message}"
                )

                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = it.message
                )
            }
        }
    }

    private fun validate(): Boolean {
        val username = uiState.username.trim()
        val password = uiState.password.trim()

        when {
            username.isBlank() -> {
                uiState = uiState.copy(
                    usernameError = "Tên đăng nhập không được để trống"
                )
                println(uiState)
                return false
            }

            password.isBlank() -> {
                uiState = uiState.copy(
                    passwordError = "Mật khẩu không được để trống"
                )
                println(uiState)
                return false
            }

            password.length < 6 -> {
                uiState = uiState.copy(
                    passwordError = "Mật khẩu phải có ít nhất 6 ký tự"
                )
                println(uiState)
                return false
            }

        }

        return true
    }
}