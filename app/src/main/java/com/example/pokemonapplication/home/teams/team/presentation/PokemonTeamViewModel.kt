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
    private val pokemonTeamRepository: PokemonTeamRepository
) : ViewModel() {

    private val _teamId = MutableLiveData<Int>()
    val teamId: LiveData<Int> = _teamId
    private val _isAddButtonEnable = MutableLiveData<Boolean>()
    val isAddButtonEnable: LiveData<Boolean> = _isAddButtonEnable
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _newPokemon = MutableLiveData<Pokemon>()
    val newPokemon: LiveData<Pokemon> = _newPokemon

    init {
        _isAddButtonEnable.postValue(true)
        _teamId.postValue(0)
    }

    fun createTeam() {
        _loading.postValue(true)
        viewModelScope.launch {
            _teamId.postValue(pokemonTeamRepository.createTeam("time"))
            _isAddButtonEnable.postValue(true)
            _loading.postValue(false)
        }
    }

    fun getPokemonTeam() {
        viewModelScope.launch {
            val pokeTeam = pokemonTeamRepository.getTeam(0)
            searchPokemon(pokeTeam)
        }
    }

    private suspend fun searchPokemon(pokemonTeam: PokemonTeam?) {
        if (pokemonTeam?.pokemonList != null) {
            for (pokemonId in pokemonTeam.pokemonList!!) {
                _newPokemon.postValue(
                    pokemonTeamRepository.searchPokemon(pokemonId)
                )
            }
        }
    }

}
