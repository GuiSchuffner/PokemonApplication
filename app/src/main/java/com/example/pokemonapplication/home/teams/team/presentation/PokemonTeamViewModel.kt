package com.example.pokemonapplication.home.teams.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.PokemonTeam
import com.example.pokemonapplication.home.teams.team.data.PokemonTeamRepository
import kotlinx.coroutines.launch

class PokemonTeamViewModel(
    private val teamId: Int,
    private val pokemonTeamRepository: PokemonTeamRepository
) : ViewModel() {

    private val _isAddButtonEnable = MutableLiveData<Boolean>()
    val isAddButtonEnable: LiveData<Boolean> = _isAddButtonEnable
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _pokemonTeam = MutableLiveData<List<Pokemon>>()
    val pokemonTeam: LiveData<List<Pokemon>> = _pokemonTeam

    init {
        getPokemonTeam()
    }

    fun getPokemonTeam() {
        _loading.postValue(true)
        _isAddButtonEnable.postValue(false)
        viewModelScope.launch {
            val pokeTeam = pokemonTeamRepository.getTeam(teamId)
            searchPokemon(pokeTeam)
            _isAddButtonEnable.postValue(true)
            _loading.postValue(false)
        }
    }

    private suspend fun searchPokemon(pokemonTeam: PokemonTeam?) {
        val pokemonList = mutableListOf<Pokemon>()
        if (pokemonTeam?.pokemonList != null) {
            for (pokemonId in pokemonTeam.pokemonList!!) {
                pokemonList.add(pokemonTeamRepository.searchPokemon(pokemonId))
            }
        }
        _pokemonTeam.postValue(pokemonList)
    }

}
