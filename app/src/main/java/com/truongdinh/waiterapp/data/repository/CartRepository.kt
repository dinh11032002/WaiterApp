package com.truongdinh.waiterapp.data.repository

import android.util.Log
import com.truongdinh.waiterapp.data.local.dao.CartDao
import com.truongdinh.waiterapp.data.local.entity.CartEntity
import com.truongdinh.waiterapp.data.remote.api.CartApi
import com.truongdinh.waiterapp.data.remote.dto.CartActionRequest
import com.truongdinh.waiterapp.data.remote.dto.CartDto
import com.truongdinh.waiterapp.data.remote.dto.CartReplaceRequest
import com.truongdinh.waiterapp.domain.model.Cart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val cartDao: CartDao,
    private val cartApi: CartApi
) {
    companion object {
        private const val TAG = "CartRepository"
    }

    fun getCarts(tableId: Int): Flow<List<Cart>> {
        return cartDao.getCarts(tableId)
            .map { entities ->
                entities.map {
                    Cart(
                        tableId = it.tableId,
                        menuItemId = it.menuItemId,
                        quantity = it.quantity
                    )
                }
            }
    }

    suspend fun syncCart(tableId: Int) {
        try {
            val response = cartApi.getCarts(tableId)
            syncToRoom(tableId, response)
        } catch (exception: Exception) {
            Log.e(TAG, "syncCart() failed: ${exception.message}")
        }
    }

    suspend fun add(tableId: Int, menuItemId: Int) {
        try {
            val response = cartApi.addItem(CartActionRequest(tableId, menuItemId))
            syncToRoom(tableId, response)

        } catch (exception: Exception) {
            Log.e(TAG, "add() failed, fallback to local: ${exception.message}")
            addLocal(tableId, menuItemId)
        }
    }

    suspend fun increase(tableId: Int, menuItemId: Int) {
        cartDao.increaseQuantity(tableId, menuItemId)
    }

    suspend fun decrease(tableId: Int, menuItemId: Int) {
        try {
            val response = cartApi.decreaseItem(CartActionRequest(tableId, menuItemId))
            syncToRoom(tableId, response)
        } catch (exception: Exception) {
            Log.e(TAG, "decrease() failed, fallback to local: ${exception.message}")
            cartDao.decreaseQuantity(tableId, menuItemId)
        }
    }

    suspend fun delete(tableId: Int, menuItemId: Int) {
        try {
            val response = cartApi.deleteItem(tableId, menuItemId)
            syncToRoom(tableId, response)

        } catch (exception: Exception) {
            Log.e(TAG, "delete() failed, fallback to local: ${exception.message}")
            cartDao.deleteItem(tableId, menuItemId)
        }
    }

    suspend fun replaceDrink(tableId: Int, oldMenuItemId: Int, newMenuItemId: Int) {
        try {
            val response = cartApi.replaceItem(CartReplaceRequest(tableId, oldMenuItemId, newMenuItemId))
            syncToRoom(tableId, response)
        } catch (exception: Exception) {
            Log.e(TAG, "replaceDrink() failed: ${exception.message}")
        }
    }

    suspend fun clear(tableId: Int) {
        try {
            val response = cartApi.clearCart(tableId)
            syncToRoom(tableId, response)
        } catch (exception: Exception) {
            Log.e(TAG, "clear() failed: ${exception.message}")
            cartDao.clearTable(tableId)
        }
    }

    suspend fun clearCart() {
        cartDao.clear()
    }

    private suspend fun syncToRoom(tableId: Int, items: List<CartDto>) {
        cartDao.clearTable(tableId)
        cartDao.insertCart(
            items.map {
                CartEntity(
                    tableId = it.tableId,
                    menuItemId = it.menuItemId,
                    quantity = it.quantity
                )
            }
        )
    }

    private suspend fun addLocal(tableId: Int, menuItemId: Int) {
        val existing = cartDao.getCart(tableId, menuItemId)
        if (existing != null) {
            cartDao.increaseQuantity(tableId, menuItemId)
        } else {
            cartDao.insertCart(
                CartEntity(
                    tableId = tableId,
                    menuItemId = menuItemId,
                    quantity = 1
                )
            )
        }
    }
}