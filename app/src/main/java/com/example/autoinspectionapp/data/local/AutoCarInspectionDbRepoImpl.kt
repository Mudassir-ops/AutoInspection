package com.example.autoinspectionapp.data.local

import com.example.autoinspectionapp.domain.AutoInspectionLocalRepo.AutoCarInspectionDbRepo

class AutoCarInspectionDbRepoImpl(
    private val dao: AutoCarInspectionDao
) : AutoCarInspectionDbRepo {

    override suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?) {
        info?.let { dao.insertPreliminaryInfo(info = info) }
    }

}