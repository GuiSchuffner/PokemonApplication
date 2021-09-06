package com.example.pokemonapplication

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.favorites.data.FavoritesRepository
import com.example.pokemonapplication.home.favorites.presentation.FavoritesViewModel
import com.example.pokemonapplication.home.search.data.SearchPokemonNameRepository
import com.example.pokemonapplication.home.search.data.SearchPokemonTypeRepository
import com.example.pokemonapplication.home.search.presentation.SearchPokemonNameViewModel
import com.example.pokemonapplication.home.search.presentation.SearchPokemonTypeViewModel
import com.example.pokemonapplication.home.search.presentation.SearchPokemonViewModel
import com.example.pokemonapplication.home.teams.team.data.PokemonTeamRepository
import com.example.pokemonapplication.home.teams.team.presentation.PokemonTeamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val initialModule = module{
    viewModel {
        SearchPokemonViewModel()
    }
    viewModel { (pokemonName: String, searchIntent: Int, teamId: Int) ->
        SearchPokemonNameViewModel(pokemonName, searchIntent, teamId, get())
    }
    viewModel { (pokemonType: String) ->
        SearchPokemonTypeViewModel(pokemonType, get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
    viewModel {
        PokemonTeamViewModel(get())
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
    factory {
        PokemonTeamRepository(get())
    }
}
