package com.truongdinh.waiterapp.ui.features.order.detail

data class OrderDetailUiState(
    val orderDetailUiModel: OrderDetailUiModel? = null,
    val totalAmount: Long = 0L,
    val isLoading: Boolean = false,
    val messageError: String? = null
)
