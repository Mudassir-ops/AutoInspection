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

}