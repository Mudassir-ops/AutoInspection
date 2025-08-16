package com.example.autoinspectionapp.domain.autoInspectionLocalRepo

import com.example.autoinspectionapp.data.local.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.BodyStructureFunctionEntity
import com.example.autoinspectionapp.data.local.ElectricalSafetyFunctionEntity
import com.example.autoinspectionapp.data.local.InteriorControlFunctionEntity
import com.example.autoinspectionapp.data.local.MechanicalFunctionEntity
import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity
import com.example.autoinspectionapp.data.local.SuspensionSteeringFunctionEntity
import com.example.autoinspectionapp.data.local.TyreFunctionEntity
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

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
}