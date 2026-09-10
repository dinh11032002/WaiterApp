package com.truongdinh.waiterapp.ui.features.order.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.truongdinh.waiterapp.component.CommonHeader
import com.truongdinh.waiterapp.component.HeaderTitleAlignment
import com.truongdinh.waiterapp.domain.model.OrderStatus
import com.truongdinh.waiterapp.ui.features.order.detail.component.OrderDetailList
import com.truongdinh.waiterapp.ui.features.order.detail.component.OrderInformation
import com.truongdinh.waiterapp.ui.features.order.detail.component.OrderTotal
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderDetailScreen(
    uiState: OrderDetailUiState?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Đơn hàng#${uiState?.orderDetailUiModel?.orderId}",
                titleAlignment = HeaderTitleAlignment.Start,
                onBackClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = AppSpacing.lg)
        ) {
            uiState?.orderDetailUiModel?.let {
                OrderInformation(
                    uiModel = it
                )
            }

            Spacer(modifier = Modifier.height(AppSpacing.md))

            OrderDetailList(
                orderDetails = uiState?.orderDetailUiModel?.orderItemUiModel ?: emptyList()
            )

            Spacer(modifier = Modifier.weight(1f))

            uiState?.orderDetailUiModel?.orderItemUiModel?.let {
                OrderTotal(
                    orderItems = it
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7_pro"
)
@Composable
fun OrderDetailPreview() {
    WaiterAppTheme {
        OrderDetailScreen(
            uiState = OrderDetailUiState(
                orderDetailUiModel = OrderDetailUiModel(
                    orderId = 1,
                    tableId = 1,
                    tableName = "Bàn 1",
                    status = OrderStatus.OPEN,
                    staffId = 1,
                    staffName = "Trương Đình",
                    orderItemUiModel = listOf(
                        OrderItemUiModel(
                            orderItemId = 1,
                            menuItemId = 1,
                            name = "Cà phê đen",
                            image = "",
                            quantity = 1,
                            unitPrice = 25000L
                        )
                    ),
                    createdAt = LocalDateTime.of(2026, 8, 20, 11, 3)
                ),
                totalAmount = 100000L
            )
        )
    }
}