package com.example.capstone.ui.pages.recipe

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.capstone.di.Injection

class RecipeViewModel(private val context: Context): ViewModel() {
    fun getAllRecipe() = Injection.recipeProvideRepository(context).getallRecipe()
}