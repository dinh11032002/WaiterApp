package com.truongdinh.waiterapp.ui.features.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.truongdinh.waiterapp.ui.features.cart.CartItemUiModel
import com.truongdinh.waiterapp.ui.theme.AppSize
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.util.toCurrencyFormat

@Composable
fun CartItemCard(
    cartItem: CartItemUiModel,
    onItemClick: (CartItemUiModel) -> Unit,
    onIncreaseClick: (Int) -> Unit,
    onDecreaseClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(AppSpacing.lg),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onItemClick(cartItem)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(AppSpacing.md)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = cartItem.image,
                contentDescription = "image_order_item",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(AppSize.drinkImageMedium)
                    .clip(RoundedCornerShape(AppSpacing.md))
            )

            Spacer(modifier = Modifier.width(AppSpacing.md))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cartItem.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    IconButton(
                        onClick = {
                            onDeleteClick(cartItem.menuItemId)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "icon_delete",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Số lượng: ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            onDecreaseClick(cartItem.menuItemId)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "decrease_icon",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Text(
                            text = "${cartItem.quantity}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        IconButton(onClick = {
                            onIncreaseClick(cartItem.menuItemId)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "increase_icon",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                Text(
                    text = "Giá: ${cartItem.price.toCurrencyFormat()}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}