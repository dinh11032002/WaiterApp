package com.truongdinh.waiterapp.data.remote.dto

data class OrderDetailDto(
    val orderId: Int,
    val tableId: Int,
    val staffId: Int,
    val status: String,
    val createdAt: String,
    val items: List<OrderItemDetailDto>
)
