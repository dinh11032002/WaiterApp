package com.truongdinh.waiterapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.data.local.entity.OrderEntity
import com.truongdinh.waiterapp.domain.model.Order
import com.truongdinh.waiterapp.domain.model.OrderStatus
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun OrderEntity.toDomain(): Order {
    val parsedDate = try {
        val timestamp = createdAt.toLongOrNull()
        if (timestamp != null) {
            Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        } else {
            LocalDateTime.parse(createdAt)
        }
    } catch (e: Exception) {
        LocalDateTime.now()
    }

    return Order(
        id = id,
        tableId = tableId,
        staffId = staffId,
        status = runCatching {
            OrderStatus.valueOf(status)
        }.getOrDefault(OrderStatus.OPEN),
        createdAt = parsedDate
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        tableId = tableId,
        staffId = staffId,
        status = status.name,
        createdAt = createdAt.toString()
    )
}