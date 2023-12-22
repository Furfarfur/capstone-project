package com.example.capstone.data.repository

import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class FridgeRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
){
    suspend fun saveSeasoin(user: String){
        userPreference.saveSession(user)
    }

     fun getSession(): Flow<String>{
         return userPreference.getSession()
     }

    suspend fun logout(){
        userPreference.logout()
    }
    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ): FridgeRepository = FridgeRepository(apiService, userPreference)
    }
}