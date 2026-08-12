package com.truongdinh.waiterapp.data.remote.dto

data class CartReplaceRequest(
    val tableId: Int,
    val oldMenuItemId: Int,
    val newMenuItemId: Int
)
