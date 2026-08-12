package com.truongdinh.waiterapp.data.repository

import android.util.Log
import com.truongdinh.waiterapp.data.local.dao.CategoryDao
import com.truongdinh.waiterapp.data.local.entity.CategoryEntity
import com.truongdinh.waiterapp.data.mapper.toDomain
import com.truongdinh.waiterapp.data.remote.api.CategoryApi
import com.truongdinh.waiterapp.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryApi: CategoryApi
) {
    fun getCategories() : Flow<List<Category>> {
        return categoryDao.getCategories()
            .map {entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
    }

    suspend fun syncCategories() {
        try {
            val response = categoryApi.getCategories()
            val entities = response.map { dto ->
                CategoryEntity(
                    id = dto.id,
                    name = dto.name
                )
            }
            categoryDao.insertCategories(entities)
        } catch (exception: Exception) {
            Log.e("CategoryRepository", "Sync failed: ${exception.message}")
        }
    }
}