package com.truongdinh.waiterapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.data.local.dao.OrderDao
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.data.mapper.toEntity
import com.truongdinh.waiterapp.domain.model.Order
import com.truongdinh.waiterapp.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) {
    suspend fun insertOrder(order: Order): Long {
        return orderDao.insertOrder(order.toEntity())
    }

    fun getOrderById(orderId: Int): Flow<Order?> {
        return orderDao
            .getOrderById(orderId)
            .map { entity ->
                entity?.toDomain()
            }
    }


    fun getOrderByTableId(tableId: Int): Flow<List<Order>> {
        return orderDao
            .getOrdersByTableId(tableId)
            .map { entities ->
                entities.map {
                    it.toDomain()
                }
            }
    }

    fun observeOrders(): Flow<List<Order>> {
        return orderDao
            .observeOrders()
            .map { orders ->
                orders.map {
                    it.toDomain()
                }
            }
    }

    suspend fun updateOrderStatus(orderId: Int, status: OrderStatus) {
        orderDao.updateOrderStatus(orderId = orderId, status = status.name)
    }
}