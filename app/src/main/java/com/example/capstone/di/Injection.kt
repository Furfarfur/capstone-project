package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.local.database.RecipeDatabase
import com.example.capstone.data.local.pref.UserPreference
import com.example.capstone.data.local.pref.dataStore
import com.example.capstone.data.remote.retrofit.ApiConfig
import com.example.capstone.data.repository.FridgeRepository
import com.example.capstone.data.repository.IngredientRepository
import com.example.capstone.utils.Executor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): FridgeRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user)
        return FridgeRepository.getInstance(apiService, pref)
    }

    fun recipeProvideRepository(context: Context): IngredientRepository{
        val apiService = ApiConfig.getApiService(null)
        val database = RecipeDatabase.getInstance(context)
        val dao = database.recipeDao()
        val appExecutor = Executor()

        return IngredientRepository.getInstance(apiService, dao, appExecutor)
    }
}