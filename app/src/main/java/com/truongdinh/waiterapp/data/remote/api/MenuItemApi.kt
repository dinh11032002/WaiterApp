package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.MenuItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuItemApi {
    @GET("/api/v1/menu-items")
    suspend fun getMenuItems(
        @Query("categoryId") categoryId: Int? = null,
        @Query("name") name: String? = null
    ): List<MenuItemDto>
}