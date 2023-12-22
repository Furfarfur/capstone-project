package com.example.capstone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipes")
data class RecipeEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "missedIngredient")
    var missedIngredient: String,

    @ColumnInfo(name = "usedIngredient")
    var usedIngredient: String,

    @ColumnInfo(name = "likes")
    var likes: String
    ): Serializable
