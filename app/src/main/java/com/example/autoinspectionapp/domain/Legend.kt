package com.example.autoinspectionapp.domain

import android.graphics.PointF

data class Legend(val code: String, val description: String, val legendFilledColor: Int)
data class PartWithDamage(
    val point: PointF,
    val code: String,
    val color: Int,
    val partName: String
)