package com.truongdinh.waiterapp.data.mapper

import com.truongdinh.waiterapp.data.local.entity.CartEntity
import com.truongdinh.waiterapp.domain.model.Cart

fun CartEntity.toDomain(): Cart {
    return Cart(
        tableId =  tableId,
        menuItemId = menuItemId,
        quantity = quantity
    )
}

fun Cart.toEntity(): CartEntity {
    return CartEntity(
        tableId =  tableId,
        menuItemId = menuItemId,
        quantity = quantity
    )
}