package com.truongdinh.waiterapp.ui.features.order

data class OrderUiState(
    val orders: List<OrderUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
