package com.example.autoinspectionapp.domain.autoInspectionLocalRepo

import com.example.autoinspectionapp.data.local.ACHeaterFunctionEntity
import com.example.autoinspectionapp.data.local.MechanicalFunctionEntity
import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

interface AutoCarInspectionDbRepo {

    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity?)
    suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity?)
    suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity?)
}