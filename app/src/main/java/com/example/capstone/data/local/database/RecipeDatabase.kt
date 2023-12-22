package com.example.capstone.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone.data.local.dao.RecipeDAO
import com.example.capstone.data.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase(){
    abstract fun recipeDao(): RecipeDAO

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): RecipeDatabase {
            if (instance == null) {
                synchronized(RecipeDatabase::class.java) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        RecipeDatabase::class.java, "recipes.db")
                        .build()
                }
            }
            return instance as RecipeDatabase
        }
    }
}