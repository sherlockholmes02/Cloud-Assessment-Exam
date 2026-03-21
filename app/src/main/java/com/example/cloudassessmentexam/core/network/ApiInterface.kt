package com.example.cloudassessmentexam.core.network

import com.example.cloudassessmentexam.data.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("users?site=stackoverflow")
    suspend fun getUsers(): Response<UsersResponse>
}