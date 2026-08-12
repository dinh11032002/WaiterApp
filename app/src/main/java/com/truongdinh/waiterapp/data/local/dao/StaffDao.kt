package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.StaffEntity

@Dao
interface StaffDao {
    @Query("""
        SELECT * FROM staffs
        WHERE username = :username
        AND password = :password
        LIMIT 1
        """)
    suspend fun signIn(username: String, password: String): StaffEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaff(staff: StaffEntity)

    @Query("SELECT COUNT(*) FROM staffs")
    suspend fun getStaffCount(): Int
}