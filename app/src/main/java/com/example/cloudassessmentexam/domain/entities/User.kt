package com.example.cloudassessmentexam.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("display_name")
    val displayName: String?,
    val reputation: Int?,
    val location: String?,
    @SerializedName("creation_date")
    val memberSince: Long?,
)