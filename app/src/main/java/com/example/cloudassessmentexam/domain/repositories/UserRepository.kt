package com.example.cloudassessmentexam.domain.repositories

import com.example.cloudassessmentexam.data.data_sources.UsersResponse
import com.example.cloudassessmentexam.data.models.BaseUiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<BaseUiState<UsersResponse>>
}