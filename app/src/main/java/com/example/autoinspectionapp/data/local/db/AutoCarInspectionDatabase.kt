package com.example.autoinspectionapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.autoinspectionapp.data.local.dao.AutoCarInspectionDao
import com.example.autoinspectionapp.data.local.entities.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.entities.AccidentChecklistEntity
import com.example.autoinspectionapp.data.local.entities.BodyStructureFunctionEntity
import com.example.autoinspectionapp.data.local.entities.ElectricalSafetyFunctionEntity
import com.example.autoinspectionapp.data.local.entities.InteriorControlFunctionEntity
import com.example.autoinspectionapp.data.local.entities.MechanicalFunctionEntity
import com.example.autoinspectionapp.data.local.entities.PreliminaryInfoEntity
import com.example.autoinspectionapp.data.local.entities.SparePartsFunctionEntity
import com.example.autoinspectionapp.data.local.entities.SuspensionSteeringFunctionEntity
import com.example.autoinspectionapp.data.local.entities.TestDriveInspectionEntity
import com.example.autoinspectionapp.data.local.entities.TyreFunctionEntity
import com.example.autoinspectionapp.data.local.typeconverters.Converters

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
    version = 2, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AutoCarInspectionDatabase : RoomDatabase() {
    abstract fun autoCarInspectionDao(): AutoCarInspectionDao
}