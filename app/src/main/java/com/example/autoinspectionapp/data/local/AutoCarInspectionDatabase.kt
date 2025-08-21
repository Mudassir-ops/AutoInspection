package com.example.autoinspectionapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

@Database(
    entities = [
        PreliminaryInfoEntity::class,
        AccidentChecklistEntity::class,
        MechanicalFunctionEntity::class,
        ACHeaterFunctionEntity::class,
        InteriorControlFunctionEntity::class,
        ElectricalSafetyFunctionEntity::class,
        SuspensionSteeringFunctionEntity::class,
        BodyStructureFunctionEntity::class,
        TyreFunctionEntity::class,
        SparePartsFunctionEntity::class,
        TestDriveInspectionEntity::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AutoCarInspectionDatabase : RoomDatabase() {
    abstract fun autoCarInspectionDao(): AutoCarInspectionDao
}