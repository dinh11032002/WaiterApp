package com.truongdinh.waiterapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.data.local.entity.OrderEntity
import com.truongdinh.waiterapp.domain.model.Order
import com.truongdinh.waiterapp.domain.model.OrderStatus
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun OrderEntity.toDomain(): Order {
    return Order(
        id = id,
        tableId = tableId,
        staffId = staffId,
        status = OrderStatus.valueOf(status),
        createdAt = LocalDateTime.parse(createdAt)
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        tableId = tableId,
        staffId = staffId,
        status = status.name,
        createdAt = createdAt.toString()
    )
}