package com.example.capstone.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.data.local.entity.RecipeEntity

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipes")
    fun getRecipes(): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipes(recipe: List<RecipeEntity>)

    @Query("DELETE FROM recipes WHERE id IS NOT NULL")
    fun deleteRecipes()
}