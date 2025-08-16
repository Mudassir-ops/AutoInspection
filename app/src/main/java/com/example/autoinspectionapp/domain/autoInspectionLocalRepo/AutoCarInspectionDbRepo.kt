package com.example.autoinspectionapp.domain.autoInspectionLocalRepo

import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

interface AutoCarInspectionDbRepo {

    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity?)

}