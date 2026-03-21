package com.example.cloudassessmentexam.data.repositories_impl

import com.example.cloudassessmentexam.core.network.ApiInterface
import com.example.cloudassessmentexam.core.network.apiRequestFlow
import com.example.cloudassessmentexam.data.UsersResponse
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
) : UserRepository {
    override fun getUsers(): Flow<BaseUiState<UsersResponse>> = apiRequestFlow {
        apiInterface.getUsers()
    }
}