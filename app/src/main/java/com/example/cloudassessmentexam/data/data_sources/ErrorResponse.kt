package com.example.cloudassessmentexam.data.data_sources

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerializedName("error_id")
    val code: Int,
    @SerializedName("error_message")
    val message: String
)