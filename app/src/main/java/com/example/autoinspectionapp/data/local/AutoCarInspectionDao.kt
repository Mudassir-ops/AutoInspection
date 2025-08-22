package com.example.autoinspectionapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoinspectionapp.domain.AccidentChecklistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AutoCarInspectionDao {

    //--Insert Methods

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

    ///--Get Methods
    @Query("select * from body_structure_function_table")
    fun getBodyExterior(): Flow<BodyStructureFunctionEntity?>


}
