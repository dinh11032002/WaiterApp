package com.truongdinh.waiterapp.data.local.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "orders")
data class OrderEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tableId: Int = 0,
    val staffId: Int = 0,
    val status: String = "",
    val createdAt: String = LocalDateTime.now().toString()
)
