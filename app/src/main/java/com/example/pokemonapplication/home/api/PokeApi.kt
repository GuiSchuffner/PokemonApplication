package com.example.pokemonapplication.home.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokeApi {


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