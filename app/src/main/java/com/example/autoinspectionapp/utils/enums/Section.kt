package com.example.autoinspectionapp.utils.enums

enum class Section(val displayName: String, val position: Int) {
    PRELIMINARY_INFO("Preliminary Info", 0),
    ACCIDENTAL_CHECKLIST("Accidental Checklist", 1),
    MECHANICAL_FUNCTION("Mechanical Function", 2),
    AC_HEATER_OPERATION("A/C & Heater", 3),
    INTERIOR("Interior", 4),
    ELECTRONIC_FUNCTION("Electronic Function", 5),
    SUSPENSION_FUNCTION("Suspension Function", 6),
    EXTERIOR_BODY("Exterior Body", 7),
    TYRES("Tyres", 8),
    ACCESSORIES("Accessories", 9),
    TEST_DRIVE("Test Drive", 10),
    SAVE_SEND("Save Send", 11);
}