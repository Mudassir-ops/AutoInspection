package com.example.autoinspectionapp.data.local.repository

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
import com.example.autoinspectionapp.domain.local.repository.AutoCarInspectionDbRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull

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

    override suspend fun insertSparePartsFunctionEntity(sparePartsFunction: SparePartsFunctionEntity?) {
        sparePartsFunction?.let {
            dao.insertSparePartsFunctionEntity(sparePartsFunction = it)
        }
    }

    override suspend fun insertTestDriveInspectionEntity(testDriveInspectionEntity: TestDriveInspectionEntity?) {
        testDriveInspectionEntity?.let {
            dao.insertTestDriveInspectionEntity(testDriveInspectionEntity = it)
        }
    }

    override fun getBodyExterior(): Flow<BodyStructureFunctionEntity?> {
        return dao.getBodyExterior()
    }

    override fun getPreliminaryData(): Flow<PreliminaryInfoEntity?> {
        return dao.getPreliminaryData()
    }

    override fun getAccidentChecklistData(): Flow<AccidentChecklistEntity?> {
        return dao.getAccidentChecklistData()
    }

    override fun getMechanicalFunctionData(): Flow<MechanicalFunctionEntity?> {
        return dao.getMechanicalFunctionData()
    }

    override fun getACHeaterFunctionData(): Flow<ACHeaterFunctionEntity?> {
        return dao.getACHeaterFunctionData()
    }

    override fun getInteriorControlFunctionData(): Flow<InteriorControlFunctionEntity?> {
        return dao.getInteriorControlFunctionData()
    }

    override fun getElectricalSafetyFunctionData(): Flow<ElectricalSafetyFunctionEntity?> {
        return dao.getElectricalSafetyFunctionData()
    }

    override fun getSuspensionSteeringFunctionData(): Flow<SuspensionSteeringFunctionEntity?> {
        return dao.getSuspensionSteeringFunctionData()
    }

    override fun getBodyStructureFunctionData(): Flow<BodyStructureFunctionEntity?> {
        return dao.getBodyStructureFunctionData()
    }

    override fun getTyreFunctionData(): Flow<TyreFunctionEntity?> {
        return dao.getTyreFunctionData()
    }

    override fun getSparePartsFunctionData(): Flow<SparePartsFunctionEntity?> {
        return dao.getSparePartsFunctionData()
    }

    override fun getTestDriveInspectionData(): Flow<TestDriveInspectionEntity?> {
        return dao.getTestDriveInspectionData()
    }

    /**
     * Checks if any table has data
     */
    override val isAnyDataPresentFlow: Flow<Boolean> = combine(
        dao.getPreliminaryData(),
        dao.getAccidentChecklistData(),
        dao.getMechanicalFunctionData(),
        dao.getACHeaterFunctionData(),
        dao.getInteriorControlFunctionData(),
        dao.getElectricalSafetyFunctionData(),
        dao.getSuspensionSteeringFunctionData(),
        dao.getBodyStructureFunctionData(),
        dao.getTyreFunctionData(),
        dao.getSparePartsFunctionData(),
        dao.getTestDriveInspectionData()
    ) { values ->
        values.any { it != null }
    }


}