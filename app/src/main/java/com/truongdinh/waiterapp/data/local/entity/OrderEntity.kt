package com.truongdinh.waiterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tableId: Int = 0,
    val staffId: Int = 0,
    val status: String = "",
    val createdAt: String
)
