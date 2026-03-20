package com.example.cloudassessmentexam.data

data class User(
    val id: String,
    val name: String,
    val avatarURL: String,
    val username: String,
    val displayName: String,
    val reputation: Int,
    val memberSince: String,
)
