package com.truongdinh.waiterapp.ui.features.order.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.truongdinh.waiterapp.ui.features.order.detail.OrderItemUiModel
import com.truongdinh.waiterapp.ui.theme.AppSize
import com.truongdinh.waiterapp.ui.theme.AppSpacing

@Composable
fun OrderDetailCard(
    orderItemUiModel: OrderItemUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(AppSpacing.lg),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.lg)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.md)
            ) {
                AsyncImage(
                    model = orderItemUiModel.image,
                    contentDescription = "image_order_item",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(AppSize.drinkImageMedium)
                        .clip(RoundedCornerShape(AppSpacing.md))
                )

                Spacer(modifier = Modifier.width(AppSpacing.sm))

                Column {
                    Text(
                        text = "Tên đồ uống: ${orderItemUiModel.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.sm))

                    Text(
                        text = "Số lượng: ${orderItemUiModel.quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.height(AppSpacing.sm))

                    Text(
                        text = "Giá: ${orderItemUiModel.unitPrice}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}