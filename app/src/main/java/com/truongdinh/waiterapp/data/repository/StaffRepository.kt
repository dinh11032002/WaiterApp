package com.truongdinh.waiterapp.data.repository

import com.truongdinh.waiterapp.data.remote.api.AuthApi
import com.truongdinh.waiterapp.data.local.dao.StaffDao
import com.truongdinh.waiterapp.data.local.entity.Shift
import com.truongdinh.waiterapp.data.local.entity.StaffEntity
import com.truongdinh.waiterapp.data.remote.dto.ApiStatus
import com.truongdinh.waiterapp.data.remote.dto.LoginRequest
import javax.inject.Inject

class StaffRepository @Inject constructor(
    private val staffDao: StaffDao,
    private val authApi: AuthApi
) {
    suspend fun signIn(username: String, password: String): Result<StaffEntity> {
        return try {
            val response = authApi.login(LoginRequest(username, password))
            if (response.status == ApiStatus.SUCCESS && response.staff != null) {
                val staff = StaffEntity(
                    id = response.staff.id,
                    username = response.staff.username,
                    fullName = response.staff.fullName,
                    password = "",
                    shift = Shift.valueOf(response.staff.shift)
                )
                staffDao.insertStaff(staff)
                Result.success(staff)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (exception: Exception) {
            val staff = staffDao.signIn(
                username = username,
                password = password
            )
            if (staff != null) Result.success(staff)
            else Result.failure(exception = Exception("Không thể kết nối đến máy chủ"))
        }
    }
}