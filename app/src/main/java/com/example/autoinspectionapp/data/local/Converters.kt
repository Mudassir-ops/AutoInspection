package com.example.autoinspectionapp.data.local

import androidx.room.TypeConverter
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPartDamageSummary(value: PartDamageSummary?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPartDamageSummary(value: String?): PartDamageSummary? {
        return if (value.isNullOrEmpty()) null
        else gson.fromJson(value, PartDamageSummary::class.java)
    }

    // --- List of PartDamageSummary ---
    @TypeConverter
    fun fromList(value: List<PartDamageSummary>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String?): List<PartDamageSummary> {
        return if (value.isNullOrEmpty()) emptyList()
        else gson.fromJson(value, object : TypeToken<List<PartDamageSummary>>() {}.type)
    }
}

