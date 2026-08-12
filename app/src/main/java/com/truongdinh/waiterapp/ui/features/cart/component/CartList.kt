package com.truongdinh.waiterapp.ui.features.cart.component

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
import com.truongdinh.waiterapp.ui.features.cart.CartItemUiModel
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun CartList(
    cartItems: List<CartItemUiModel>,
    onItemClick: (CartItemUiModel) -> Unit,
    onIncreaseClick: (Int) -> Unit,
    onDecreaseClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
) {
    if (cartItems.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Không có đồ uống nào được thêm vào giỏ hàng",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    } else {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md)
    ) {
        items(cartItems, key = { it.menuItemId }) { items ->
            CartItemCard(
                cartItem = items,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
                onDeleteClick = onDeleteClick,
                onItemClick = onItemClick
                )
            }
        }
    }
}