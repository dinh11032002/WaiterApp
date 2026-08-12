package com.truongdinh.waiterapp.data.repository

import android.util.Log
import com.truongdinh.waiterapp.data.remote.api.TableApi
import com.truongdinh.waiterapp.data.local.dao.TableDao
import com.truongdinh.waiterapp.data.local.entity.TableEntity
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.domain.model.Table
import com.truongdinh.waiterapp.domain.model.TableStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class TableRepository @Inject constructor(
    private val tableDao: TableDao,
    private val tableApi: TableApi
) {
    fun getTables(): Flow<List<Table>> {
        return tableDao.getTables()
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
    }

    suspend fun syncTables() {
        try {
            val response = tableApi.getTables()
            val entities = response.map { dto ->
                TableEntity(
                    id = dto.id,
                    name = dto.name,
                    status = dto.status
                )
            }
            tableDao.insertTables(entities)
        } catch (exception: Exception) {
            Log.e("TableRepository", "Sync failed: ${exception.message}")
        }
    }

    suspend fun getTableById(tableId: Int): Table? {
        return tableDao.getTableById(tableId)?.toDomain()
    }

    fun searchTables(query: String): Flow<List<Table>> {
        return tableDao.searchTables(query).map { entities ->
            entities.map {
                it.toDomain()
            }
        }
    }

    suspend fun updateTableStatus(tableId: Int, status: TableStatus) {
        tableDao.updateStatus(tableId, status.name)
    }
}