package com.example.autoinspectionapp.domain

data class PartDamageSummary(
    val partName: String,
    val damageCodes: List<PartWithDamage>
)
