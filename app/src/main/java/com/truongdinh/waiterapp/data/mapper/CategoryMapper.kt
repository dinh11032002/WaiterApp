package com.truongdinh.waiterapp.data.mapper

import com.truongdinh.waiterapp.data.local.entity.CategoryEntity
import com.truongdinh.waiterapp.domain.model.Category

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name
    )
}