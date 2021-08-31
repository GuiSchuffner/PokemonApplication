package com.example.pokemonapplication

import com.example.pokemonapplication.home.searchpokemon.presentation.SearchPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val initialModule = module{
    viewModel {
        SearchPokemonViewModel()
    }
}