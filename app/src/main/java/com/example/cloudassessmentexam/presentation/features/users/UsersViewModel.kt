package com.example.cloudassessmentexam.presentation.features.users

import com.example.cloudassessmentexam.core.network.CoroutineErrorHandler
import com.example.cloudassessmentexam.core.viewmodel.BaseViewModel
import com.example.cloudassessmentexam.data.User
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.domain.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): BaseViewModel() {
    private val _usersFlow: MutableStateFlow<BaseUiState<List<User>>> =
        MutableStateFlow(BaseUiState.Uninitialized)

    val usersFlow: StateFlow<BaseUiState<List<User>>> get() = _usersFlow

    fun loadUsers(coroutineErrorHandler: CoroutineErrorHandler) = baseRequest(
        _usersFlow,
        coroutineErrorHandler,
    ) {
        getUsersUseCase.execute()
    }
}