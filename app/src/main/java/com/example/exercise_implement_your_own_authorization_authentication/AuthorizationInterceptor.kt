package com.example.exercise_implement_your_own_authorization_authentication

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://dog.ceo/api/breeds/list/all")
            .get()
            .addHeader("Cache-Control","no-cache")
            .addHeader("User-Agent",System.getProperty("http.agent"))
            .build()

        return try {
            client.newCall(request).execute()
        } catch (e: Exception) {
            Log.d("MainActivity","error ${e.message}")
            throw e
        }
    }
}