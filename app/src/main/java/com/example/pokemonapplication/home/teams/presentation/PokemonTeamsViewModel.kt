package com.example.pokemonapplication.home.teams.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.PokemonTeam
import com.example.pokemonapplication.home.teams.data.PokemonTeamsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class PokemonTeamsViewModel(
    private val pokemonTeamsRepository: PokemonTeamsRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _teamsList = MutableLiveData<List<PokemonTeam>>()
    val teamsList: LiveData<List<PokemonTeam>> = _teamsList
    private val _isTeamsListEmpty = MutableLiveData<Boolean>()
    val isTeamsListEmpty: LiveData<Boolean> = _isTeamsListEmpty


    init {
        listenPokemonTeams()
    }

    /*fun getPokemonTeams() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val list = pokemonTeamsRepository.getTeam()
                if (list != null) {
                    _teamsList.postValue(list)
                    _isTeamsListEmpty.postValue(false)
                } else {
                    _isTeamsListEmpty.postValue(true)
                }
            } catch (e: Exception) {

            }
            _loading.postValue(false)
        }
    }*/

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    fun listenPokemonTeams() {
        viewModelScope.launch {
            pokemonTeamsRepository.getTeams().collect {
                when {
                    it.isSuccess -> {
                        val list = it.getOrNull()
                        if (list != null) {
                            _teamsList.postValue(list)
                            _isTeamsListEmpty.postValue(false)
                        } else {
                            _isTeamsListEmpty.postValue(true)
                        }
                    }
                    it.isFailure -> {

                    }
                }
            }
        }
    }

    fun removeTeam(teamId: String) {
        pokemonTeamsRepository.removeTeam(teamId)
    }

}
