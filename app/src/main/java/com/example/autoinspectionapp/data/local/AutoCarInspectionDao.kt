package com.example.autoinspectionapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.autoinspectionapp.domain.AccidentChecklistEntity

@Dao
interface AutoCarInspectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccidentChecklist(accidentChecklistEntity: AccidentChecklistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMechanicalFunction(mechanicalFunction: MechanicalFunctionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertACHeaterFunctionEntity(acHeaterFunction: ACHeaterFunctionEntity)

}
