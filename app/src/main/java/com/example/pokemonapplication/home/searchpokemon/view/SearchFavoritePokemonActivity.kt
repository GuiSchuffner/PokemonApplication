package com.example.pokemonapplication.home.searchpokemon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapplication.databinding.ActivitySearchFavoritePokemonBinding

class SearchFavoritePokemonActivity: AppCompatActivity() {
    private lateinit var binding : ActivitySearchFavoritePokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFavoritePokemonBinding.inflate(layoutInflater)
    }
}
