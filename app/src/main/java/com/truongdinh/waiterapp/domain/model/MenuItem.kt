package com.truongdinh.waiterapp.domain.model

data class MenuItem(
    val id: Int,
    val name: String,
    val image: String,
    val price: Long,
    val categoryId: Int,
    val isAvailable: Boolean
)