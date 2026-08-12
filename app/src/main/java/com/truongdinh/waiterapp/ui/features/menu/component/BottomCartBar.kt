package com.truongdinh.waiterapp.ui.features.menu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.ui.theme.AppRadius
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BottomCartBar(
    tableName: String,
    totalItems: Int,
    totalPrices: Long,
    onBottomCartBarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onBottomCartBarClick,
        shape = RoundedCornerShape(AppRadius.lg),
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxWidth()
            .padding(AppSpacing.md)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .padding(AppSpacing.lg)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.xs),
        ) {
            Text(
                text = "$tableName • $totalItems món",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(AppSpacing.sm))

            Text(
                text = "${NumberFormat.getNumberInstance(Locale("vi", "VN")).format(totalPrices)} VNĐ",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Spacer(modifier = Modifier.width(AppSpacing.lg))

            Icon(
                imageVector = Icons.Filled.ArrowRightAlt,
                contentDescription = "cart_icon",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}