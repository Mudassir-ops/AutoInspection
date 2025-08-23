package com.example.autoinspectionapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.autoinspectionapp.data.local.dao.AutoCarInspectionDao
import com.example.autoinspectionapp.data.local.db.AutoCarInspectionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideNotepadDatabase(@ApplicationContext context: Context): AutoCarInspectionDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AutoCarInspectionDatabase::class.java,
            "auto_car_inspection_database"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideNoteDao(database: AutoCarInspectionDatabase): AutoCarInspectionDao {
        return database.autoCarInspectionDao()
    }
}
