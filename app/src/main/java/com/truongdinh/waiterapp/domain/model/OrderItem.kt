package com.truongdinh.waiterapp.domain.model

data class OrderItem(
    val id: Int,
    val orderId: Int,
    val menuItemId: Int,
    val quantity: Int,
    val unitPrice: Long
)