package com.truongdinh.waiterapp.data.mapper

import com.truongdinh.waiterapp.data.local.entity.MenuItemEntity
import com.truongdinh.waiterapp.domain.model.MenuItem

fun MenuItemEntity.toDomain(): MenuItem {
    return MenuItem(
        id = id,
        name = name,
        image = image,
        price = price,
        categoryId = categoryId,
        isAvailable = isAvailable
    )
}

fun MenuItem.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = id,
        name = name,
        image = image,
        price = price,
        categoryId = categoryId,
        isAvailable = isAvailable
    )
}