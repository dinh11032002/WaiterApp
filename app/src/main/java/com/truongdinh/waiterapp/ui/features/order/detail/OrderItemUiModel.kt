package com.truongdinh.waiterapp.ui.features.order.detail

data class OrderItemUiModel(
    val orderItemId: Int,
    val menuItemId: Int,
    val name: String,
    val image: String,
    val quantity: Int,
    val unitPrice: Long
)
