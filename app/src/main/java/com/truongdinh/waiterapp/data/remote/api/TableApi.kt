package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.TableDto
import com.truongdinh.waiterapp.data.remote.dto.UpdateTableStatusRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface TableApi {
    @GET("/api/v1/tables")
    suspend fun getTables(
        @Query("status") status: String? = null,
        @Query("query") query: String? = null
    ) : List<TableDto>

    @PATCH("/api/v1/tables/{tableId}/status")
    suspend fun updateTableStatus(
        @Path("tableId") tableId: Int,
        @Body request: UpdateTableStatusRequest
    ): TableDto
}