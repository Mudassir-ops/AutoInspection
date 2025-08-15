package com.example.autoinspectionapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AutoCarInspectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreliminaryInfo(info: PreliminaryInfoEntity)

}
