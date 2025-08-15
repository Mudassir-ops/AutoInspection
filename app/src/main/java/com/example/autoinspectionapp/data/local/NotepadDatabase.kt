package com.example.autoinspectionapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =
        [PreliminaryInfoEntity::class],
    version = 1, exportSchema = false
)

abstract class AutoCarInspectionDatabase : RoomDatabase() {
    abstract fun autoCarInspectionDao(): AutoCarInspectionDao
}