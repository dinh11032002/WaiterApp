package com.truongdinh.waiterapp.ui.features.order

import com.truongdinh.waiterapp.domain.model.OrderStatus
import java.time.LocalDateTime

data class OrderUiModel(
    val id: Int,
    val tableName: String,
    val status: OrderStatus,
    val createAt: LocalDateTime
)
