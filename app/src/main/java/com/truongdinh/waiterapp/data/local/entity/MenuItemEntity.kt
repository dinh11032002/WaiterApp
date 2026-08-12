package com.truongdinh.waiterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val price: Long = 0L,
    val image: String = "",
    val categoryId: Int,
    val isAvailable: Boolean = false
)