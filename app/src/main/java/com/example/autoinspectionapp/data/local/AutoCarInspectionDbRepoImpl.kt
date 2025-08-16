package com.example.autoinspectionapp.data.local

import com.example.autoinspectionapp.domain.AccidentChecklistEntity
import com.example.autoinspectionapp.domain.autoInspectionLocalRepo.AutoCarInspectionDbRepo

class AutoCarInspectionDbRepoImpl(
    private val dao: AutoCarInspectionDao
) : AutoCarInspectionDbRepo {

    override suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?) {
        info?.let { dao.insertPreliminaryInfo(info = it) }
    }

    override suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity?) {
        accidentChecklistEntity?.let { dao.insertAccidentChecklist(accidentChecklistEntity = it) }
    }

    override suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity?) {
        mechanicalFunction?.let { dao.insertMechanicalFunction(mechanicalFunction = it) }
    }

    override suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity?) {
        acHeaterFunction?.let { dao.insertACHeaterFunctionEntity(acHeaterFunction = it) }
    }

    override suspend fun insertInteriorControlFunctionEntity(interiorControlFunction: InteriorControlFunctionEntity?) {
        interiorControlFunction?.let {
            dao.insertInteriorControlFunctionEntity(
                interiorControlFunction = it
            )
        }
    }

    override suspend fun insertElectricalSafetyFunctionEntity(electricalSafetyFunction: ElectricalSafetyFunctionEntity?) {
        electricalSafetyFunction?.let {
            dao.insertElectricalSafetyFunctionEntity(
                electricalSafetyFunction = it
            )
        }
    }

    override suspend fun insertSuspensionSteeringFunctionEntity(suspensionSteeringFunction: SuspensionSteeringFunctionEntity?) {
        suspensionSteeringFunction?.let {
            dao.insertSuspensionSteeringFunctionEntity(
                suspensionSteeringFunction = it
            )
        }
    }

    override suspend fun insertBodyStructureFunctionEntity(bodyStructureFunction: BodyStructureFunctionEntity?) {
        bodyStructureFunction?.let {
            dao.insertBodyStructureFunctionEntity(
                bodyStructureFunction = it
            )
        }
    }

    override suspend fun insertTyreFunctionEntity(tyreFunction: TyreFunctionEntity?) {
        tyreFunction?.let {
            dao.insertTyreFunctionEntity(tyreFunction = it)
        }
    }

}