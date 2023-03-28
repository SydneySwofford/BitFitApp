package com.example.recyclerview

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Query("SELECT * FROM blood_pressure_table")
    fun getAll(): Flow<List<Item>>

    @Insert
    fun insert(item: Item)

    @Query("DELETE FROM blood_pressure_table")
    fun deleteAll()
}