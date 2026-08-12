package com.truongdinh.waiterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truongdinh.waiterapp.data.local.entity.TableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {
    @Query("SELECT * FROM tables")
    fun getTables(): Flow<List<TableEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTables(tables: List<TableEntity>)

    @Query("SELECT * FROM tables WHERE id = :tableId")
    suspend fun getTableById(tableId: Int): TableEntity?

    @Query("""SELECT * FROM tables WHERE LOWER(name) = LOWER(:query) OR LOWER(name) LIKE LOWER(:query || ' %')""")
    fun searchTables(query: String): Flow<List<TableEntity>>

    @Query("UPDATE tables SET status = :status WHERE id = :tableId")
    suspend fun updateStatus(tableId: Int, status: String)
}