package com.example.recyclerview
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "Blood_Pressure_Table")
 data class Item(

    @ColumnInfo(name="date")val entrydate: String?,
    @ColumnInfo(name = "reading") val reading: String?,
    @ColumnInfo(name = "mood") val mood: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    )