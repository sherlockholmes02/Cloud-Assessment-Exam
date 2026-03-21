package com.example.cloudassessmentexam.data.models

sealed interface BaseUiState<out T> {

    object Loading : BaseUiState<Nothing>

    object Uninitialized : BaseUiState<Nothing>

    data class Failure(val errorMessage: String, val code: Int) : BaseUiState<Nothing>

    data class Success<T>(val data: T) : BaseUiState<T>
}