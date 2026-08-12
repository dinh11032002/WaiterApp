package com.truongdinh.waiterapp.ui.features.cart

data class CartItemUiModel(
    val menuItemId: Int,
    val draftOrderId: Int,
    val name: String,
    val image: String,
    val quantity: Int,
    val price: Long
)