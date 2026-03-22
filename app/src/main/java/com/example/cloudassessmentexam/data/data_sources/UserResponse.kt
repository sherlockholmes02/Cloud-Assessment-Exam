package com.example.cloudassessmentexam.data.data_sources

import com.example.cloudassessmentexam.domain.entities.User

data class UsersResponse(
    val items: List<User> = emptyList()
)