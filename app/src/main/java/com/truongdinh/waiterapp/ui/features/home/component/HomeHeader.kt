package com.truongdinh.waiterapp.ui.features.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.data.local.entity.Shift
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun HomeHeader(
    username: String? = null,
    shift: Shift? = null,
    modifier: Modifier = Modifier
) {
    val shiftText = when (shift) {
        Shift.MORNING -> {
            "Ca sáng"
        }

        Shift.EVENING -> {
            "Ca chiều"
        }

        null -> {
            "Chưa có ca"
        }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Xin chào, $username",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(AppSpacing.xs))

        Text(
            text = shiftText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}