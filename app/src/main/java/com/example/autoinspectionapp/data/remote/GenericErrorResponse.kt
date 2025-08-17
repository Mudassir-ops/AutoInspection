package com.example.autoinspectionapp.data.remote

data class GenericErrorResponse(
    val errors: Errors? = null,
    val message: String? = null,
    val errorType: String? = null,
    val path: String? = null,
    val statusCode: Int? = null
)

data class Errors(
    val email: List<String>,
    val phone: List<String>,
    val companyPostalCode: List<String>,
    val website: List<String>
)