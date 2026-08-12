package com.truongdinh.waiterapp.ui.features.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.truongdinh.waiterapp.ui.features.auth.signin.component.SignInButton
import com.truongdinh.waiterapp.ui.features.auth.signin.component.SignInHeader
import com.truongdinh.waiterapp.ui.features.auth.signin.component.SignInPasswordField
import com.truongdinh.waiterapp.ui.features.auth.signin.component.SignUsernameField
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme

@Composable
fun SignInScreen(
    state: SignInUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(paddingValues = innerPadding)
                .padding(horizontal = AppSpacing.lg)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SignInHeader(
                title = "Đăng nhập"
            )

            Spacer(modifier = Modifier.height(AppSpacing.xxl))

            SignUsernameField(
                username = state.username,
                onUsernameChange = onUsernameChange,
                label = "Tên đăng nhập",
                isError = state.usernameError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.sm))

            SignInPasswordField(
                password = state.password,
                onPasswordChange = onPasswordChange,
                label = "Mật khẩu",
                isError = state.passwordError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.xl))

            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(AppSpacing.sm))
            }

            SignInButton(
                label = "Đăng nhập",
                onClick = onSignInClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7_pro"
)
@Composable
fun SignInPreview() {
    WaiterAppTheme {
        SignInScreen(
            state = SignInUiState(
                username = "",
                password = ""
            ),
            onUsernameChange = {},
            onPasswordChange = {},
            onSignInClick = {}
        )
    }
}