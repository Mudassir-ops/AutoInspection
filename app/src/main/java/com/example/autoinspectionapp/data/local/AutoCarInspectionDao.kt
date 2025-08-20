package com.example.autoinspectionapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

@Dao
interface AutoCarInspectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInteriorControlFunctionEntity(interiorControlFunction: InteriorControlFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElectricalSafetyFunctionEntity(electricalSafetyFunction: ElectricalSafetyFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuspensionSteeringFunctionEntity(suspensionSteeringFunction: SuspensionSteeringFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBodyStructureFunctionEntity(bodyStructureFunction: BodyStructureFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTyreFunctionEntity(tyreFunction: TyreFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSparePartsFunctionEntity(sparePartsFunction: SparePartsFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestDriveInspectionEntity(testDriveInspectionEntity: TestDriveInspectionEntity)

}
