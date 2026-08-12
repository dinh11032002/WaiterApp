package com.truongdinh.waiterapp.ui.features.order

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
import com.truongdinh.waiterapp.domain.model.OrderStatus
import com.truongdinh.waiterapp.ui.features.order.component.OrderList
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(
    uiState: OrderUiState,
    onOrderClick: (OrderUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Đơn hàng"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(horizontal = AppSpacing.lg)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(AppSpacing.md))

            OrderList(
                orders = uiState.orders,
                onOrderClick = onOrderClick
            )
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
fun OrderPreview() {
    WaiterAppTheme {
        OrderScreen(
            uiState = OrderUiState(
                orders = listOf(
                    OrderUiModel(
                        id = 1,
                        tableName = "Bàn 1",
                        status = OrderStatus.PREPARING,
                        createAt = LocalDateTime.of(2026, 8, 10, 9, 30)
                    )
                )
            ),
            onOrderClick = {}
        )
    }
}