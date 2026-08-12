package com.truongdinh.waiterapp.domain.model

import java.time.LocalDateTime

data class Order(
    val id: Int,
    val tableId: Int,
    val staffId: Int,
    val status: OrderStatus,
    val createdAt: LocalDateTime
)
