package com.truongdinh.waiterapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.data.local.dao.OrderDao
import com.truongdinh.waiterapp.data.local.dao.OrderItemDao
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.data.mapper.toEntity
import com.truongdinh.waiterapp.data.remote.api.OrderApi
import com.truongdinh.waiterapp.data.remote.dto.OrderDetailDto
import com.truongdinh.waiterapp.data.remote.dto.OrderItemRequest
import com.truongdinh.waiterapp.data.remote.dto.PlaceOrderRequest
import com.truongdinh.waiterapp.domain.model.Order
import com.truongdinh.waiterapp.domain.model.OrderItem
import com.truongdinh.waiterapp.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao,
    private val orderApi: OrderApi
) {
    suspend fun placeOrder(order: Order, items: List<OrderItem>): Long {
        return try {
            val placed = orderApi.placeOrder(
                PlaceOrderRequest(
                    tableId = order.tableId,
                    items = items.map {
                        OrderItemRequest(it.menuItemId, it.quantity)
                    }
                )
            )
            val detail = orderApi.getOrderDetail(placed.orderId)
            saveDetailToRoom(detail)
            detail.orderId.toLong()
        } catch (e: IOException) {
            val localId = orderDao.insertOrder(order.toEntity())
            orderItemDao.insertOrderItems(
                items.map { it.copy(orderId = localId.toInt()).toEntity() }
            )
            localId
        }
    }

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

    private suspend fun saveDetailToRoom(d: OrderDetailDto) {
        orderDao.insertOrder(
            Order(
                id = d.orderId,
                tableId = d.tableId,
                staffId = d.staffId,
                status = OrderStatus.valueOf(d.status),
                createdAt = LocalDateTime.now()
            ).toEntity()
        )
        orderItemDao.insertOrderItems(
            d.items.map {
                OrderItem(
                    id = it.orderItemId,
                    orderId = d.orderId,
                    menuItemId = it.menuItemId,
                    quantity = it.quantity,
                    unitPrice = it.unitPrice
                ).toEntity()
            }
        )
    }
}