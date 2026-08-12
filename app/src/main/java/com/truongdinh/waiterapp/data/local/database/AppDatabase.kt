package com.truongdinh.waiterapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.truongdinh.waiterapp.data.local.dao.CategoryDao
import com.truongdinh.waiterapp.data.local.dao.CartDao
import com.truongdinh.waiterapp.data.local.dao.MenuItemDao
import com.truongdinh.waiterapp.data.local.dao.OrderDao
import com.truongdinh.waiterapp.data.local.dao.OrderItemDao
import com.truongdinh.waiterapp.data.local.dao.StaffDao
import com.truongdinh.waiterapp.data.local.dao.TableDao
import com.truongdinh.waiterapp.data.local.entity.CategoryEntity
import com.truongdinh.waiterapp.data.local.entity.CartEntity
import com.truongdinh.waiterapp.data.local.entity.MenuItemEntity
import com.truongdinh.waiterapp.data.local.entity.OrderEntity
import com.truongdinh.waiterapp.data.local.entity.OrderItemEntity
import com.truongdinh.waiterapp.data.local.entity.StaffEntity
import com.truongdinh.waiterapp.data.local.entity.TableEntity

@Database(
    entities = [
        TableEntity::class,
        StaffEntity::class,
        MenuItemEntity::class,
        CategoryEntity::class,
        OrderItemEntity::class,
        CartEntity::class,
        OrderEntity::class
    ],
    version = 12,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tableDao(): TableDao

    abstract fun staffDao() : StaffDao

    abstract fun menuItemDao() : MenuItemDao

    abstract fun categoryDao() : CategoryDao

    abstract fun orderItemDao() : OrderItemDao

    abstract fun cartDao() : CartDao

    abstract fun orderDao() : OrderDao
}