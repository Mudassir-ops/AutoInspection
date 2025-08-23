package com.example.autoinspectionapp.domain.local.repository

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

interface AutoCarInspectionDbRepo {

    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity?)
    suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity?)
    suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity?)
    suspend fun insertInteriorControlFunctionEntity(interiorControlFunction: InteriorControlFunctionEntity?)
    suspend fun insertElectricalSafetyFunctionEntity(electricalSafetyFunction: ElectricalSafetyFunctionEntity?)
    suspend fun insertSuspensionSteeringFunctionEntity(suspensionSteeringFunction: SuspensionSteeringFunctionEntity?)
    suspend fun insertBodyStructureFunctionEntity(bodyStructureFunction: BodyStructureFunctionEntity?)
    suspend fun insertTyreFunctionEntity(tyreFunction: TyreFunctionEntity?)
    suspend fun insertSparePartsFunctionEntity(sparePartsFunction: SparePartsFunctionEntity?)
    suspend fun insertTestDriveInspectionEntity(testDriveInspectionEntity: TestDriveInspectionEntity?)

    fun getBodyExterior(): Flow<BodyStructureFunctionEntity?>
}