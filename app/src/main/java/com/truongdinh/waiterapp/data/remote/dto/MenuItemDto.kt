package com.truongdinh.waiterapp.data.remote.dto

data class MenuItemDto(
    val id: Int,
    val name: String,
    val price: Long,
    val image: String,
    val isAvailable: Boolean,
    val categoryId: Int
)
