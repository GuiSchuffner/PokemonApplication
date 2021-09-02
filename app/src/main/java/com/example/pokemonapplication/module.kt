package com.example.pokemonapplication

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.favorites.data.SearchPokemonRepository
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonNameViewModel
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val initialModule = module{
    viewModel {
        SearchPokemonViewModel()
    }
    viewModel { (pokemonName: String) ->
        SearchPokemonNameViewModel(pokemonName, get())
    }
    factory {
        PokeApi.create()
    }
    factory {
        SearchPokemonRepository(get())
    }
}
