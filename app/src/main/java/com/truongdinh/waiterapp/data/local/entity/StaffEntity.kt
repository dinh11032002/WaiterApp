package com.truongdinh.waiterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staffs")
data class StaffEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String = "",
    val fullName: String = "",
    val password: String = "",
    val shift: Shift
)
