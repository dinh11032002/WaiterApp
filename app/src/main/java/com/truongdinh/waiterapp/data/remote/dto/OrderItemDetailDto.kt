package com.truongdinh.waiterapp.data.remote.dto

data class OrderItemDetailDto(
    val orderItemId: Int,
    val menuItemId: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Long,
    val image: String?
)
