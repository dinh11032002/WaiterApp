package com.truongdinh.waiterapp.ui.features.cart.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun CartInfoCard(
    tableName: String,
    tableStatus: TableStatus,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = tableName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(AppSpacing.sm))

        Text(
            text = "Trạng thái bàn: ${tableStatus.value}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}