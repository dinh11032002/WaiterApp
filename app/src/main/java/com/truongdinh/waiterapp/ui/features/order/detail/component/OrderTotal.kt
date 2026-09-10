package com.truongdinh.waiterapp.ui.features.order.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.ui.features.order.detail.OrderItemUiModel
import com.truongdinh.waiterapp.util.toCurrencyFormat

@Composable
fun OrderTotal(
    orderItems: List<OrderItemUiModel>,
    modifier: Modifier = Modifier
) {
    val totalAmount = orderItems.sumOf { it.quantity * it.unitPrice }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Tổng tiền: ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = totalAmount.toCurrencyFormat(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}