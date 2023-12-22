package com.example.capstone.ui.pages.upload

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.capstone.di.Injection
import okhttp3.MultipartBody

class UploadViewModel(private val context: Context): ViewModel() {
    fun insertIngredient(file: MultipartBody.Part) = Injection.recipeProvideRepository(context).insertIngredient(file)
}