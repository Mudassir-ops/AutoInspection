package com.example.autoinspectionapp.data.local

import androidx.room.TypeConverter
import com.example.autoinspectionapp.domain.PartDamageSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PartDamageSummaryListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPartDamageSummaryList(summaryList: List<PartDamageSummary>?): String? {
        return summaryList?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPartDamageSummaryList(jsonString: String?): List<PartDamageSummary>? {
        return jsonString?.let {
            val listType = object : TypeToken<List<PartDamageSummary>>() {}.type
            gson.fromJson(it, listType)
        }
    }
}