package com.example.pokemonapplication.home.teams.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.teams.team.data.PokemonTeamRepository
import com.example.pokemonapplication.home.util.SingleLiveEvent
import kotlinx.coroutines.launch

class PokemonTeamViewModel(
    private val teamId: String,
    private val pokemonTeamRepository: PokemonTeamRepository
) : ViewModel() {

    private val _isAddButtonEnable = MutableLiveData<Boolean>()
    val isAddButtonEnable: LiveData<Boolean> = _isAddButtonEnable
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _addPokemon = SingleLiveEvent<Unit>()
    val addPokemon: LiveData<Unit> = _addPokemon
    private val _pokemonRemoved = SingleLiveEvent<Unit>()
    val pokemonRemoved: LiveData<Unit> = _pokemonRemoved

    private var pokemonListSize: Int = 0


    init {
        getPokemonTeam()
    }

    fun getPokemonTeam() {
        _loading.postValue(true)
        _isAddButtonEnable.postValue(false)
        viewModelScope.launch {
            try {
                val pokeTeam = pokemonTeamRepository.getTeam(teamId)
                searchPokemon(pokeTeam)
            } catch (e: Exception) {
                _error.postValue("Erro Desconhecido !")
            }
            _isAddButtonEnable.postValue(true)
            _loading.postValue(false)
        }
    }

    fun removePokemon(pokemonId: Int) {
        if (!loading.value!!) {
            _loading.postValue(true)
            _isAddButtonEnable.postValue(false)
            viewModelScope.launch {
                try {
                    pokemonTeamRepository.removePokemon(teamId, pokemonId)
                    _pokemonRemoved.postValue(Unit)
                } catch (e: Exception) {
                    _error.postValue("Erro Desconhecido !")
                }
                _loading.postValue(false)
                _isAddButtonEnable.postValue(true)
            }
        }
    }

    fun onAddPokemonButtonClicked() {
        if (pokemonListSize < 6) {
            _addPokemon.postValue(Unit)
        } else {
            _error.postValue("Seu Time está cheio")
        }
    }

    private suspend fun searchPokemon(pokemonTeam: List<Int>?) {
        val newPokemonList = mutableListOf<Pokemon>()
        pokemonListSize = if (pokemonTeam != null) {
            for (pokemonId in pokemonTeam) {
                newPokemonList.add(pokemonTeamRepository.searchPokemon(pokemonId))
            }
            newPokemonList.size
        } else {
            0
        }
        _pokemonList.postValue(newPokemonList)
    }

}
