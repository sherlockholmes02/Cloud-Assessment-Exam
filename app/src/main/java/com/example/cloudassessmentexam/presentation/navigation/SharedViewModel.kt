package com.example.cloudassessmentexam.presentation.navigation

import androidx.lifecycle.ViewModel
import com.example.cloudassessmentexam.domain.entities.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    fun selectUser(user: User) {
        _selectedUser.value = user
    }
}