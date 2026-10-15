package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.OrderDetailDto
import com.truongdinh.waiterapp.data.remote.dto.PlaceOrderRequest
import com.truongdinh.waiterapp.data.remote.dto.PlaceOrderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST("api/v1/orders")
    suspend fun placeOrder(@Body request: PlaceOrderRequest): PlaceOrderResponse

    @GET("api/v1/orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Long): OrderDetailDto
}