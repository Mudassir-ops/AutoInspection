package com.example.autoinspectionapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
import kotlinx.coroutines.flow.Flow

@Dao
interface AutoCarInspectionDao {

    //--Insert Methods

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertInteriorControlFunctionEntity(interiorControlFunction: InteriorControlFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertElectricalSafetyFunctionEntity(electricalSafetyFunction: ElectricalSafetyFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSuspensionSteeringFunctionEntity(suspensionSteeringFunction: SuspensionSteeringFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertBodyStructureFunctionEntity(bodyStructureFunction: BodyStructureFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertTyreFunctionEntity(tyreFunction: TyreFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSparePartsFunctionEntity(sparePartsFunction: SparePartsFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertTestDriveInspectionEntity(testDriveInspectionEntity: TestDriveInspectionEntity)

    ///--Get Methods
    @Query("select * from body_structure_function_table")
    fun getBodyExterior(): Flow<BodyStructureFunctionEntity?>

    @Query("SELECT * FROM preliminary_info_table LIMIT 1")
    fun getPreliminaryData(): Flow<PreliminaryInfoEntity?>

    @Query("SELECT * FROM accident_checklist_table LIMIT 1")
    fun getAccidentChecklistData(): Flow<AccidentChecklistEntity?>

    @Query("SELECT * FROM mechanical_function_table LIMIT 1")
    fun getMechanicalFunctionData(): Flow<MechanicalFunctionEntity?>

    @Query("SELECT * FROM ac_heater_function_table LIMIT 1")
    fun getACHeaterFunctionData(): Flow<ACHeaterFunctionEntity?>

    @Query("SELECT * FROM interior_control_function_table LIMIT 1")
    fun getInteriorControlFunctionData(): Flow<InteriorControlFunctionEntity?>

    @Query("SELECT * FROM electrical_safety_function_table LIMIT 1")
    fun getElectricalSafetyFunctionData(): Flow<ElectricalSafetyFunctionEntity?>

    @Query("SELECT * FROM suspension_steering_function_table LIMIT 1")
    fun getSuspensionSteeringFunctionData(): Flow<SuspensionSteeringFunctionEntity?>

    @Query("SELECT * FROM body_structure_function_table LIMIT 1")
    fun getBodyStructureFunctionData(): Flow<BodyStructureFunctionEntity?>

    @Query("SELECT * FROM tyre_function_table LIMIT 1")
    fun getTyreFunctionData(): Flow<TyreFunctionEntity?>

    @Query("SELECT * FROM spare_parts_function_table LIMIT 1")
    fun getSparePartsFunctionData(): Flow<SparePartsFunctionEntity?>

    @Query("SELECT * FROM test_drive_inspection LIMIT 1")
    fun getTestDriveInspectionData(): Flow<TestDriveInspectionEntity?>



}