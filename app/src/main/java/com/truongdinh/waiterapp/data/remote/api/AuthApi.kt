package com.truongdinh.waiterapp.data.remote.api

import com.truongdinh.waiterapp.data.remote.dto.LoginRequest
import com.truongdinh.waiterapp.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}