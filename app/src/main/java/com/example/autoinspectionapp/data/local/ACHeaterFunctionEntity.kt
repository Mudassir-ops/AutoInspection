package com.example.autoinspectionapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ac_heater_function_table")
data class ACHeaterFunctionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val acInstalled: String,
    val acFan: String,
    val blowerThrow: String,
    val acCooling: String,
    val heater: String
)