package com.example.cloudassessmentexam.core.network

interface CoroutineErrorHandler {
    fun onError(message:String)
}