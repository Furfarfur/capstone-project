package com.example.capstone.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.capstone.data.local.dao.RecipeDAO
import com.example.capstone.data.local.entity.RecipeEntity
import com.example.capstone.data.local.entity.RecipeRandomEntity
import com.example.capstone.data.remote.response.IngredientResponse
import com.example.capstone.data.remote.response.RandomResponse
import com.example.capstone.data.remote.retrofit.ApiService
import com.example.capstone.utils.Executor
import com.example.capstone.utils.Result
import retrofit2.Callback
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

class IngredientRepository(private val apiService: ApiService, private val recipeDAO: RecipeDAO, private val appExecutor: Executor) {

    private val result = MediatorLiveData<Result<List<RecipeEntity>>>()

    fun insertIngredient(file: MultipartBody.Part): LiveData<Result<List<RecipeEntity>>> {
        result.value = Result.Loading
        val client = apiService.ingredient(file)
        client.enqueue(object : Callback<IngredientResponse>{
            override fun onResponse(
                call: Call<IngredientResponse>,
                response: Response<IngredientResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody =response.body()!!.recipes
                    val recipeList = ArrayList<RecipeEntity>()
                    appExecutor.diskIO.execute {
                        responseBody!!.forEach { response ->
                            val recipe = RecipeEntity(
                                id = response!!.id.toString(),
                                name = response!!.title!!,
                                photo = response!!.image!!,
                                missedIngredient = response.missedIngredients!!.toString(),
                                usedIngredient = response.usedIngredients!!.toString(),
                                likes = "Likes : "+response.likes.toString()
                            )
                            recipeList.add(recipe)
                        }
                        recipeDAO.deleteRecipes()
                        recipeDAO.insertRecipes(recipeList)
                    }
                }
                else{
                    result.value = Result.Error(response.body()!!.message.toString())
                }
            }

            override fun onFailure(call: Call<IngredientResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })

        val localData = recipeDAO.getRecipes()
        result.addSource(localData){ newData : List<RecipeEntity> ->
            result.value = Result.Success(newData)
        }

        return result
    }

    fun getallRecipe(): LiveData<List<RecipeEntity>>{
        return recipeDAO.getRecipes()
    }

//    fun getRandomRecipe(): LiveData<List<RecipeRandomEntity>>{
//        val recipes = ArrayList<RecipeRandomEntity>()
//        val client = apiService.random()
//        client.enqueue(object : Callback<RandomResponse>{
//            override fun onResponse(
//                call: Call<RandomResponse>,
//                response: Response<RandomResponse>
//            ) {
//                if (response.isSuccessful){
//                    response.body()!!.recipes!!.results!!.forEach {
//                        recipe ->
//                        val entity = RecipeRandomEntity(
//                            id = recipe!!.id.toString(),
//                            name = recipe!!.title.toString(),
//                            photo = recipe!!.image.toString(),
//                            likes = "Likes : "+10
//                        )
//                        recipes.add(entity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<RandomResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

    companion object{
        @Volatile
        private var instance: IngredientRepository? = null
        fun getInstance(
            apiService: ApiService,
            recipeDAO: RecipeDAO,
            appExecutor: Executor
        ): IngredientRepository =
            instance ?: synchronized(this){
                instance ?: IngredientRepository(apiService, recipeDAO, appExecutor)
            }.also {
                instance = it
            }
    }
}