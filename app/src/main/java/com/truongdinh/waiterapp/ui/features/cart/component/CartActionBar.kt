package com.truongdinh.waiterapp.ui.features.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.ui.theme.AppSize
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun CartActionBar(
    onOrderClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.md)
    ) {
        Button(
            onClick = onCancelClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            shape = RoundedCornerShape(AppSpacing.lg),
            modifier = Modifier
                .weight(1f)
                .height(AppSize.buttonHeight)
        ) {
            Text(
                text = "Huỷ",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            onClick = onOrderClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(AppSpacing.lg),
            modifier = Modifier
                .weight(1f)
                .height(AppSize.buttonHeight)
        ) {
            Text(
                text = "Đặt",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}