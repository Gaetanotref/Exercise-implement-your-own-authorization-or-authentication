package com.example.exercise_implement_your_own_authorization_authentication

import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("/api/breeds/list/all")
    suspend fun getData() : RetrofitData
}