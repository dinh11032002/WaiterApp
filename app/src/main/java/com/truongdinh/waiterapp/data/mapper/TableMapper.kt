package com.truongdinh.waiterapp.data.mapper

import com.truongdinh.waiterapp.data.local.entity.TableEntity
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableStatus

fun TableEntity.toDomain(): Table {
    return Table(
        id = id,
        name = name,
        status = runCatching {
            TableStatus.valueOf(status)
        }.getOrDefault(TableStatus.EMPTY)
    )
}

fun Table.toEntity(): TableEntity {
    return TableEntity(
        id = id,
        name = name,
        status = status.name
    )
}