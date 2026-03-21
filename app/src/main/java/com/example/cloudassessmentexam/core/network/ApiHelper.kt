package com.example.cloudassessmentexam.core.network

import android.util.Log
import com.example.cloudassessmentexam.data.data_sources.ErrorResponse
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

const val apiErrorMessage = "Oops! Something didn’t work as expected. Please try again."

fun <T> apiRequestFlow(call: suspend () -> Response<T>): Flow<BaseUiState<T>> = flow {
    emit(BaseUiState.Loading)
    withTimeoutOrNull(20000L) {
        val response = call()
        try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(BaseUiState.Success(data))
                }
            } else {
                response.errorBody()?.let { error ->
                    error.close()
                    val parsedError: ErrorResponse =
                        Gson().fromJson(error.charStream(), ErrorResponse::class.java)

                    when (parsedError.code) {
                        in 400..500 -> emit(BaseUiState.Failure(apiErrorMessage, parsedError.code))
                        else -> emit(BaseUiState.Failure(parsedError.message, parsedError.code))
                    }

                }
            }
        } catch (e: Exception) {
            Log.e("API", e.message.toString())
            emit(BaseUiState.Failure(e.message ?: e.toString(), 400))
        }
    }
}.flowOn(Dispatchers.IO)