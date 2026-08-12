package com.truongdinh.waiterapp.data.local.session

import com.truongdinh.waiterapp.data.local.entity.Shift

data class UserSession(
    val staffId: Int = 0,
    val staffName: String? = null,
    val shift: Shift? = null,
    val isLoggedIn: Boolean = false
)
