package com.truongdinh.waiterapp.ui.features.order.detail.component

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
import com.truongdinh.waiterapp.ui.features.order.detail.OrderItemUiModel
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun OrderDetailList(
    orderDetails: List<OrderItemUiModel>,
    modifier: Modifier = Modifier
) {
    if (orderDetails.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Không có đồ uống nào trong đơn hàng",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppSpacing.md)
        ) {
            items(orderDetails, key = { it.orderItemId }) { items ->
                OrderDetailCard(
                    orderItemUiModel = items
                )
            }
        }
    }
}