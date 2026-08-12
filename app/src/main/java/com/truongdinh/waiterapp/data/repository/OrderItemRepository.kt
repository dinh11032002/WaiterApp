package com.truongdinh.waiterapp.data.repository

import com.truongdinh.waiterapp.data.local.dao.OrderItemDao
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.data.mapper.toEntity
import com.truongdinh.waiterapp.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderItemRepository @Inject constructor(
    private val orderItemDao: OrderItemDao
) {
    suspend fun insertOrderItem(orderItem: OrderItem): Long {
        return orderItemDao.insertOrderItem(orderItem.toEntity())
    }

    suspend fun insertOrderItems(orderItems: List<OrderItem>): List<Long> {
        return orderItemDao.insertOrderItems(orderItems.map { it.toEntity() })
    }

    fun getOrderItemById(orderItemId: Int): Flow<OrderItem?> {
        return orderItemDao
            .getOrderItemsById(orderItemId)
            .map { entity ->
                entity?.toDomain()
            }
    }

    fun getOrderItemsByOrderId(orderId: Int): Flow<List<OrderItem>> {
        return orderItemDao
            .getOrderItemsByOrderId(orderId)
            .map { entities ->
                entities.map {
                    it.toDomain()
                }
            }
    }

    suspend fun deleteOrderItemsByOrderId(orderId: Int) {
        orderItemDao.deleteOrderItemsByOrderId(orderId)
    }
}