package com.example.capstone.data.repository

import com.example.capstone.data.remote.response.AuthResponse
import com.example.capstone.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture


class AuthRepository(
    private val apiService: ApiService
) {
    fun insertRegister(username: String, email: String, password: String): CompletableFuture<AuthResponse> {
        val result = CompletableFuture<AuthResponse>()
        val client = apiService.register(username, email, password)
        client.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    result.complete(response.body())
                } else {
                    result.completeExceptionally(Throwable("Registration failed"))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                result.completeExceptionally(t)
            }
        })
        return result
    }

    fun login(email: String, password: String): CompletableFuture<AuthResponse>{
        val result = CompletableFuture<AuthResponse>()
        val client = apiService.login(email, password)
        client.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    result.complete(response.body())
                } else {
                    result.completeExceptionally(Throwable("Login failed"))
                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                result.completeExceptionally(t)
            }
        })
        return result
    }
}

