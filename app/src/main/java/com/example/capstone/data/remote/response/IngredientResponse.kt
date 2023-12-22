package com.example.capstone.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientResponse(

	@field:SerializedName("recipes")
	val recipes: List<RecipesItem?>? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UsedIngredientsItem(

	@field:SerializedName("originalName")
	val originalName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("meta")
	val meta: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null,

	@field:SerializedName("id")
	val id: Float? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null,

	@field:SerializedName("extendedName")
	val extendedName: String? = null
)

data class MissedIngredientsItem(

	@field:SerializedName("originalName")
	val originalName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("meta")
	val meta: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null,

	@field:SerializedName("id")
	val id: Float? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null,

	@field:SerializedName("extendedName")
	val extendedName: String? = null
)

data class RecipesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("usedIngredients")
	val usedIngredients: List<UsedIngredientsItem?>? = null,

	@field:SerializedName("missedIngredients")
	val missedIngredients: List<MissedIngredientsItem?>? = null,

	@field:SerializedName("missedIngredientCount")
	val missedIngredientCount: Float? = null,

	@field:SerializedName("unusedIngredients")
	val unusedIngredients: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Float? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null,

	@field:SerializedName("usedIngredientCount")
	val usedIngredientCount: Float? = null,

	@field:SerializedName("likes")
	val likes: Float? = null
)

data class UnusedIngredientsItem(

	@field:SerializedName("originalName")
	val originalName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("amount")
	val amount: Float? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("meta")
	val meta: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null,

	@field:SerializedName("id")
	val id: Float? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null
)
