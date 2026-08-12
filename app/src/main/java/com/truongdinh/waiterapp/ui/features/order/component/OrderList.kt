package com.truongdinh.waiterapp.ui.features.order.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.truongdinh.waiterapp.ui.features.order.OrderUiModel
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderList(
    orders: List<OrderUiModel>,
    onOrderClick: (OrderUiModel) -> Unit
) {
    if (orders.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Không có đơn hàng nào được đặt",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    } else {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md)
    ) {
        items(
            orders,
            key = {
                it.id
            }
        ) { order ->
            OrderCard(
                order = order,
                onOrderClick = onOrderClick
                )
            }
        }
    }
}