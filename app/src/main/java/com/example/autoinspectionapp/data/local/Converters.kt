package com.example.autoinspectionapp.data.local

import androidx.room.TypeConverter
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPartDamageSummary(value: PartDamageSummary?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPartDamageSummary(value: String?): PartDamageSummary? {
        return value?.let { gson.fromJson(it, PartDamageSummary::class.java) }
    }
}

