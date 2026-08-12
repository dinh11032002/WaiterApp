package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface CategoryApi {
    @GET("/api/v1/categories")
    suspend fun getCategories(): List<CategoryDto>
}