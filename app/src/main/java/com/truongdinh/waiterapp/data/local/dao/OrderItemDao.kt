package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItem: OrderItemEntity): Long

    @Insert
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>): List<Long>

    @Query("""
        SELECT * FROM order_items
        WHERE id = :orderItemId
    """)
    fun getOrderItemsById(orderItemId: Int): Flow<OrderItemEntity?>

    @Query("""
        SELECT * FROM order_items
        WHERE orderId = :orderId
    """)
    fun getOrderItemsByOrderId(orderId: Int): Flow<List<OrderItemEntity>>

    @Query("""
        DELETE FROM order_items
        WHERE orderId = :orderId
    """)
    suspend fun deleteOrderItemsByOrderId(orderId: Int)
}