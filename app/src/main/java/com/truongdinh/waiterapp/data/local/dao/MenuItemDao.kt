package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.MenuItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    fun getMenuItems(): Flow<List<MenuItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(menuItems: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_items WHERE name LIKE '%' || :query || '%'")
    fun searchMenuItem(query: String): Flow<List<MenuItemEntity>>

    @Query("SELECT * FROM menu_items WHERE categoryId = :categoryId")
    fun getMenuItemByCategory(categoryId: Int?): Flow<List<MenuItemEntity>>
}