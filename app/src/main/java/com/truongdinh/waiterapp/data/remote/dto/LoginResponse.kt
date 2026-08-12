package com.truongdinh.waiterapp.data.remote.dto

data class LoginResponse(
    val status: ApiStatus,
    val message: String,
    val staff: StaffProfileDto,
    val token: String? = null,
)
