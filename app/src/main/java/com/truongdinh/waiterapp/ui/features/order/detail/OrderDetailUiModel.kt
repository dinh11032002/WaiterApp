package com.truongdinh.waiterapp.ui.features.order.detail

import com.truongdinh.waiterapp.domain.model.OrderStatus
import java.time.LocalDateTime

data class OrderDetailUiModel(
    val orderId: Int,
    val tableId: Int,
    val staffId: Int,
    val staffName: String,
    val tableName: String,
    val status: OrderStatus,
    val orderItemUiModel: List<OrderItemUiModel>,
    val createdAt: LocalDateTime
)
