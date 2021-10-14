package com.example.pokemonapplication.home.teams.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.PokemonListItem
import com.example.pokemonapplication.home.teams.team.data.PokemonTeamRepository
import com.example.pokemonapplication.home.teams.team.model.PokemonListAdapterItem
import com.example.pokemonapplication.home.util.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class PokemonTeamViewModel(
    private val teamId: String,
    private val pokemonTeamRepository: PokemonTeamRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonListAdapterItem>>()
    val pokemonList: LiveData<List<PokemonListAdapterItem>> = _pokemonList
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _addPokemon = SingleLiveEvent<Unit>()
    val addPokemon: LiveData<Unit> = _addPokemon

    private var pokemonListSize: Int = 0


    init {
        listenToPokemonList()
    }

    @ExperimentalCoroutinesApi
    fun listenToPokemonList() {
        viewModelScope.launch {
            pokemonTeamRepository.listenPokemonTeam(teamId).collect {
                when {
                    it.isSuccess -> {
                        val list = it.getOrNull()
                        searchPokemonTeam(list!!)
                    }
                    it.isFailure -> {
                        _error.postValue("Erro Desconhecido !")
                    }
                }
            }
        }
    }

    fun removePokemon(itemKey: String) {
        viewModelScope.launch {
            try {
                pokemonTeamRepository.removePokemon(teamId, itemKey)
            } catch (e: Exception) {
                _error.postValue("Erro Desconhecido !")
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

    private suspend fun searchPokemonTeam(pokemonTeam: List<PokemonListItem>?) {
        val newPokemonList = mutableListOf<PokemonListAdapterItem>()
        pokemonListSize = if (pokemonTeam != null) {
            for (pokemonItem in pokemonTeam) {
                val pokemonListAdapterItem = PokemonListAdapterItem(
                    pokemonTeamRepository.searchPokemon(pokemonItem.pokemonId!!),
                    pokemonItem
                )
                newPokemonList.add(pokemonListAdapterItem)
            }
            newPokemonList.size
        } else {
            0
        }
        _pokemonList.postValue(newPokemonList)
    }

}
