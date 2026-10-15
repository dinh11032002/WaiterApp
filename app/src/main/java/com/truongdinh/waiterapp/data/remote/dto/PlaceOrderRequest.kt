package com.truongdinh.waiterapp.data.remote.dto

data class PlaceOrderRequest(
    val tableId: Int,
    val items: List<OrderItemRequest>
)
