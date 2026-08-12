package com.truongdinh.waiterapp.ui.features.cart

import com.truongdinh.waiterapp.domain.model.TableStatus

data class CartUiState(
    val tableId: Int = 0,
    val tableName: String = "",
    val cartItemUiModel: List<CartItemUiModel> = emptyList(),
    val totalAmount: Long = 0L,
    val tableStatus: TableStatus = TableStatus.EMPTY,
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)