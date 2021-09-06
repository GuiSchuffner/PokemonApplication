package com.example.pokemonapplication

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.favorites.data.FavoritesRepository
import com.example.pokemonapplication.home.favorites.data.SearchPokemonNameRepository
import com.example.pokemonapplication.home.favorites.data.SearchPokemonTypeRepository
import com.example.pokemonapplication.home.favorites.presentation.FavoritesViewModel
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonNameViewModel
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonTypeViewModel
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
    viewModel { (pokemonType: String) ->
        SearchPokemonTypeViewModel(pokemonType, get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
    factory {
        PokeApi.create()
    }
    factory {
        SearchPokemonNameRepository(get())
    }
    factory {
        SearchPokemonTypeRepository(get())
    }
    factory {
        FavoritesRepository(get())
    }
}
