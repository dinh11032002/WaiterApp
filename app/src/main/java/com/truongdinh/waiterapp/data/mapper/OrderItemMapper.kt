package com.truongdinh.waiterapp.data.mapper

import com.truongdinh.waiterapp.data.local.entity.OrderItemEntity
import com.truongdinh.waiterapp.domain.model.OrderItem

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(
        id = id,
        orderId = orderId,
        menuItemId = menuItemId,
        unitPrice = unitPrice,
        quantity = quantity
    )
}

fun OrderItem.toEntity(): OrderItemEntity {
    return OrderItemEntity(
        id = id,
        orderId = orderId,
        menuItemId = menuItemId,
        unitPrice = unitPrice,
        quantity = quantity
    )
}