package com.example.capstone.data.remote.retrofit

import com.example.capstone.data.remote.response.AuthResponse
import com.example.capstone.data.remote.response.IngredientResponse
import com.example.capstone.data.remote.response.RandomResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("username")name: String,
        @Field("email")email: String,
        @Field("password")password: String
    ): Call<AuthResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email")email: String,
        @Field("password")password: String
    ): Call<AuthResponse>

    @Multipart
    @POST("core/ingredient")
    fun ingredient(
        @Part file: MultipartBody.Part
    ): Call<IngredientResponse>

    @GET("core/random-recipes")
    fun random(
    ): Call<RandomResponse>

}
