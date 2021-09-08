package com.example.pokemonapplication.home.teams.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.teams.team.data.NewPokemonTeamRepository
import kotlinx.coroutines.launch

class NewPokemonTeamViewModel(
    private val newPokemonTeamRepository: NewPokemonTeamRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _createTeamEditTextError = MutableLiveData<String>()
    val createTeamEditTextError: LiveData<String> = _createTeamEditTextError
    private val _newTeamId = MutableLiveData<Int>()
    val newTeamId: LiveData<Int> = _newTeamId

    fun onCreateTeamButton(pokemonTeamName: String) {
        if (isNameValid(pokemonTeamName)) {
            _createTeamEditTextError.postValue("")
            _loading.postValue(true)
            viewModelScope.launch {
                try {
                    _newTeamId.postValue(newPokemonTeamRepository.createTeam(pokemonTeamName))
                } catch (e: Exception) {

                }
                _loading.postValue(false)
            }
        } else {
            _createTeamEditTextError.postValue("Nome invalido!!")
        }
    }

    private fun isNameValid(pokemonTeamName: String): Boolean {
        return pokemonTeamName != ""
    }

}