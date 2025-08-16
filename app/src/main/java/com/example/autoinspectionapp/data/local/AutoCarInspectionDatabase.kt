package com.example.autoinspectionapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

@Database(
    entities = [
        PreliminaryInfoEntity::class,
        AccidentChecklistEntity::class,
        MechanicalFunctionEntity::class,
        ACHeaterFunctionEntity::class,
        InteriorControlFunctionEntity::class,
        ElectricalSafetyFunctionEntity::class
    ],
    version = 1, exportSchema = false
)

abstract class AutoCarInspectionDatabase : RoomDatabase() {
    abstract fun autoCarInspectionDao(): AutoCarInspectionDao
}