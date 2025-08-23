package com.example.autoinspectionapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spare_parts_function_table")
data class SparePartsFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val spareWheel: String,
    val toolKit: String,
    val jack: String,
    val punctureRepairKit: String
)