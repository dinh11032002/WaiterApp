package com.truongdinh.waiterapp.ui.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.ui.theme.AppRadius
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun TableItem(
    table: Table,
    onClick: (Table) -> Unit,
    modifier: Modifier = Modifier
) {
    val isServing = table.status == TableStatus.SERVING

    val containerColor = if (isServing) {
        MaterialTheme.colorScheme.error.copy(0.1f)
    } else {
        MaterialTheme.colorScheme.primary.copy(0.1f)
    }

    val contentColor = if (isServing) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.primary
    }

    Card(
        onClick = {
            onClick(table)
        },
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(AppRadius.lg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.md),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.sm)
        ) {
            Text(
                text = table.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = table.status.value,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
        }
    }
}