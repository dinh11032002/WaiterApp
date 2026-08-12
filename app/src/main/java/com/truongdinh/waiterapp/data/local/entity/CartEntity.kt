package com.truongdinh.waiterapp.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "carts",
    primaryKeys = ["tableId", "menuItemId"]
)
data class CartEntity(
    val tableId: Int,
    val menuItemId: Int,
    val quantity: Int
)