package com.example.capstone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipesRandom")
data class RecipeRandomEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "likes")
    var likes: String
): Serializable