package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.TableDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TableApi {
    @GET("/api/v1/tables")
    suspend fun getTables(
        @Query("status") status: String? = null,
        @Query("query") query: String? = null
    ) : List<TableDto>
}