package com.truongdinh.waiterapp.data.repository

import android.util.Log
import com.truongdinh.waiterapp.data.local.dao.MenuItemDao
import com.truongdinh.waiterapp.data.local.entity.MenuItemEntity
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.data.mapper.toEntity
import com.truongdinh.waiterapp.data.remote.api.MenuItemApi
import com.truongdinh.waiterapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MenuItemRepository @Inject constructor(
    private val menuItemDao: MenuItemDao,
    private val menuItemApi: MenuItemApi
) {
    fun getMenuItems(): Flow<List<MenuItem>> {
        return menuItemDao.getMenuItems()
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
    }

    suspend fun syncMenuItems() {
        try {
            val response = menuItemApi.getMenuItems()
            val entities = response.map { dto ->
                MenuItemEntity(
                    id = dto.id,
                    name = dto.name,
                    price = dto.price,
                    image = dto.image,
                    isAvailable = dto.isAvailable,
                    categoryId = dto.categoryId
                )
            }
            menuItemDao.insertMenuItems(entities)
        } catch (exception: Exception) {
            Log.e("MenuItemRepository", "Sync failed: ${exception.message}")
        }
    }

    fun searchMenuItem(query: String): Flow<List<MenuItem>> {
        return menuItemDao.searchMenuItem(query)
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
    }

    fun getMenuItemByCategory(categoryId: Int): Flow<List<MenuItem>> {
        return menuItemDao.getMenuItemByCategory(categoryId)
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
    }
}