package com.example.cloudassessmentexam.presentation.features.users

import androidx.lifecycle.viewModelScope
import com.example.cloudassessmentexam.core.network.CoroutineErrorHandler
import com.example.cloudassessmentexam.core.viewmodel.BaseViewModel
import com.example.cloudassessmentexam.domain.entities.User
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.domain.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {
    private val _usersFlow: MutableStateFlow<BaseUiState<List<User>>> =
        MutableStateFlow(BaseUiState.Uninitialized)

    private val searchQuery = MutableStateFlow("")

    val filteredUsersState: StateFlow<BaseUiState<List<User>>> =
        combine(_usersFlow, searchQuery) { state, query ->
            when (state) {
                is BaseUiState.Success -> {
                    val filtered = if (query.isBlank()) state.data
                    else state.data.filter {
                        it.displayName?.contains(query, ignoreCase = true) == true
                    }
                    BaseUiState.Success(filtered)
                }
                is BaseUiState.Loading -> BaseUiState.Loading
                is BaseUiState.Failure -> state
                is BaseUiState.Uninitialized -> BaseUiState.Uninitialized
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BaseUiState.Uninitialized
        )

    fun loadUsers(coroutineErrorHandler: CoroutineErrorHandler) = baseRequest(
        liveData = _usersFlow,
        errorHandler = coroutineErrorHandler,
    ) {
        getUsersUseCase.execute()
    }

    fun onSearchQueryChange(query: String) {
        searchQuery.value = query
    }
}