package com.example.pokemonapplication.home.search.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.ActivitySearchPokemonBinding

class SearchPokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchPokemonBinding
    private val arguments by navArgs<SearchPokemonActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarPokemonSearch.setNavigationOnClickListener {
            onBackPressed()
        }
        findNavController(R.id.nav_search_favorite_pokemon_fragment)
            .setGraph(R.navigation.search_favorite_pokemon_navigation, arguments.toBundle())
    }

    companion object {
        const val SEARCH_FOR_FAVORITES = 0
        const val SEARCH_FOR_TEAM = 1
    }
}
