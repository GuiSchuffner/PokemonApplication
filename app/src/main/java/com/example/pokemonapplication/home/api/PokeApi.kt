package com.example.pokemonapplication.home.api

import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.Type
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{name}/")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Response<Pokemon>

    @GET("pokemon/{id}/")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): Response<Pokemon>

    @GET("type/{name}/")
    suspend fun getType(
        @Path("name") name: String
    ): Response<Type>

    companion object{
        private const val BASE_URL="https://pokeapi.co/api/v2/"

        fun create(): PokeApi{
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeApi::class.java)
        }
    }
}
