package com.truongdinh.waiterapp.ui.features.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.truongdinh.waiterapp.component.CommonHeader
import com.truongdinh.waiterapp.domain.model.TableStatus
import com.truongdinh.waiterapp.ui.features.cart.component.CartActionBar
import com.truongdinh.waiterapp.ui.features.cart.component.CartInfoCard
import com.truongdinh.waiterapp.ui.features.cart.component.CartList
import com.truongdinh.waiterapp.ui.features.cart.component.CartTotalRow
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.ui.theme.WaiterAppTheme

@Composable
fun CartScreen(
    uiState: CartUiState,
    onClickBack: () -> Unit,
    onOrderClick: () -> Unit,
    onCancelClick: () -> Unit,
    onItemClick: (CartItemUiModel) -> Unit,
    onIncreaseClick: (Int) -> Unit,
    onDecreaseClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Giỏ hàng",
                onBackClick = onClickBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(paddingValues = innerPadding)
                .padding(horizontal = AppSpacing.lg)
                .fillMaxSize()
        ) {
            CartInfoCard(
                tableName = uiState.tableName,
                tableStatus = uiState.tableStatus,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.md))

            CartList(
                cartItems = uiState.cartItemUiModel,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
                onDeleteClick = onDeleteClick,
                onItemClick = onItemClick
            )

            Spacer(modifier = Modifier.weight(1f))

            CartTotalRow(
                totalAmount = uiState.totalAmount,
                modifier = Modifier.padding(vertical = AppSpacing.sm)
            )

            Spacer(modifier = Modifier.height(AppSpacing.md))

            CartActionBar(
                onOrderClick = onOrderClick,
                onCancelClick = onCancelClick
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7_pro"
)
@Composable
fun OrderPreview() {
    WaiterAppTheme {
        CartScreen(
            onClickBack = {},
            onOrderClick = {},
            onCancelClick = {},
            onIncreaseClick = {},
            onDecreaseClick = {},
            onDeleteClick = {},
            uiState = CartUiState(
                cartItemUiModel = listOf(
                    CartItemUiModel(
                        image = "https://res.cloudinary.com/dq1mpgagw/image/upload/v1780885648/black_coffee_jtg2jw.png",
                        name = "Cà phê sữa",
                        quantity = 1,
                        menuItemId = 2,
                        draftOrderId = 1,
                        price = 25000L,
                    ),
                    CartItemUiModel(
                        image = "https://res.cloudinary.com/dq1mpgagw/image/upload/v1780885648/black_coffee_jtg2jw.png",
                        name = "Cà phê đen",
                        quantity = 1,
                        menuItemId = 1,
                        draftOrderId = 2,
                        price = 25000L,
                    )
                ),
                tableName = "Bàn 1",
                tableStatus = TableStatus.SERVING,
                totalAmount = 50000L
            ),
            onItemClick = {}
        )
    }
}