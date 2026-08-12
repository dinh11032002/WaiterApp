package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.CartActionRequest
import com.truongdinh.waiterapp.data.remote.dto.CartDto
import com.truongdinh.waiterapp.data.remote.dto.CartReplaceRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CartApi {
    @GET("/api/v1/carts")
    suspend fun getCarts(@Query("tableId") tableId: Int): List<CartDto>

    @POST("/api/v1/carts/add")
    suspend fun addItem(@Body request: CartActionRequest): List<CartDto>

    @POST("/api/v1/carts/decrease")
    suspend fun decreaseItem(@Body request: CartActionRequest): List<CartDto>

    @DELETE("/api/v1/carts")
    suspend fun deleteItem(
        @Query("tableId") tableId: Int,
        @Query("menuItemId") menuItemId: Int
    ): List<CartDto>

    @POST("/api/v1/carts/replace")
    suspend fun replaceItem(@Body request: CartReplaceRequest): List<CartDto>

    @DELETE("/api/v1/carts/clear")
    suspend fun clearCart(@Query("tableId") tableId: Int): List<CartDto>
}