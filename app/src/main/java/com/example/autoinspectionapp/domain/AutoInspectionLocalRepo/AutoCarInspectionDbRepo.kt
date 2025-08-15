package com.example.autoinspectionapp.domain.AutoInspectionLocalRepo

import com.example.autoinspectionapp.data.local.PreliminaryInfoEntity

interface AutoCarInspectionDbRepo {

    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity?)
}