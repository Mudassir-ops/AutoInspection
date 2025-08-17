package com.example.autoinspectionapp.data.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T? = null,
    @SerializedName("error") val error: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("success") val success: Boolean? = false
)