package com.example.cloudassessmentexam.domain.use_cases

import com.example.cloudassessmentexam.core.usecase.UseCaseNoParam
import com.example.cloudassessmentexam.domain.entities.User
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : UseCaseNoParam<Flow<BaseUiState<List<User>>>>() {
    override fun execute(): Flow<BaseUiState<List<User>>> {
        return userRepository.getUsers()
            .map { usersResponse ->
                when (usersResponse) {
                    is BaseUiState.Failure -> BaseUiState.Failure(
                        errorMessage = usersResponse.errorMessage,
                        code = usersResponse.code
                    )
                    is BaseUiState.Success ->
                        BaseUiState.Success(usersResponse.data.items)
                    BaseUiState.Loading -> BaseUiState.Loading
                    BaseUiState.Uninitialized -> BaseUiState.Uninitialized
                }
            }
    }
}
