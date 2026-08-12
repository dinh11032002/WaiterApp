package com.truongdinh.waiterapp.ui.features.auth.signin.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUsernameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    label: String,
    isError: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            shape = CircleShape,
            isError = isError != null,
            modifier = modifier
        )

        if (isError != null) {
            Text(
                text = isError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}