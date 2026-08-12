package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM carts WHERE tableId = :tableId")
    fun getCarts(tableId: Int): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(carts: CartEntity)

    @Query("""
        UPDATE carts
        SET quantity = quantity + 1
        WHERE tableId = :tableId AND menuItemId = :menuItemId 
    """)
    suspend fun increaseQuantity(tableId: Int, menuItemId: Int)

    @Query("""
        UPDATE carts
        SET quantity = quantity - 1
        WHERE tableId = :tableId AND menuItemId = :menuItemId AND quantity > 1 
    """)
    suspend fun decreaseQuantity(tableId: Int, menuItemId: Int)

    @Query("""
        DELETE FROM carts
        WHERE tableId = :tableId AND menuItemId = :menuItemId
    """)
    suspend fun deleteItem(tableId: Int, menuItemId: Int)

    @Query("""
        SELECT COUNT(*) FROM carts
        WHERE tableId = :tableId AND menuItemId = :menuItemId
    """)
    suspend fun exists(tableId: Int, menuItemId: Int): Int

    @Query("""
        SELECT * 
        FROM carts
        WHERE tableId = :tableId
            AND menuItemId = :menuItemId
        LIMIT 1
    """)
    suspend fun getCart(tableId: Int, menuItemId: Int): CartEntity?

    @Query("DELETE FROM carts")
    suspend fun clear()

    @Query("DELETE FROM carts WHERE tableId = :tableId")
    suspend fun clearTable(tableId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(items: List<CartEntity>)
}