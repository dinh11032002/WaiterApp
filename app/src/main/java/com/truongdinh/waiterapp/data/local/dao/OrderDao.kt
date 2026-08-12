package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(orderEntity: OrderEntity): Long

    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderById(orderId: Int): Flow<OrderEntity?>

    @Query("SELECT * FROM orders WHERE tableId = :tableId")
    fun getOrdersByTableId(tableId: Int): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun observeOrders(): Flow<List<OrderEntity>>

    @Query("""
        UPDATE orders
        SET status = :status
        WHERE id = :orderId
    """)
    suspend fun updateOrderStatus(orderId: Int, status: String)
}