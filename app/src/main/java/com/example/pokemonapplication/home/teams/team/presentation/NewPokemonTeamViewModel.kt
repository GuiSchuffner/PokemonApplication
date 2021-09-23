package com.example.pokemonapplication.home.teams.team.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.PokemonTeam
import com.example.pokemonapplication.home.teams.team.data.NewPokemonTeamRepository
import kotlinx.coroutines.launch

class NewPokemonTeamViewModel(
    private val newPokemonTeamRepository: NewPokemonTeamRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _createTeamEditTextError = MutableLiveData<String>()
    val createTeamEditTextError: LiveData<String> = _createTeamEditTextError
    private val _newTeamName = MutableLiveData<String>()
    val newTeamName: LiveData<String> = _newTeamName

    fun onCreateTeamButton(pokemonTeamName: String, pokemonTeamDescription: String) {
        if (isNameValid(pokemonTeamName) && isDescriptionValid(pokemonTeamDescription)) {
            _createTeamEditTextError.postValue("")
            _loading.postValue(true)
            viewModelScope.launch {
                try {
                    _newTeamName.postValue(
                        newPokemonTeamRepository.createTeam(
                            PokemonTeam(pokemonTeamName, pokemonTeamDescription)
                        )
                    )
                } catch (e: Exception) {

                }
                _loading.postValue(false)
            }
        } else {
            _createTeamEditTextError.postValue("Nome ou descrição invalidos!!")
        }
    }

    private fun isDescriptionValid(pokemonTeamDescription: String): Boolean {
        return pokemonTeamDescription != ""
    }

    private fun isNameValid(pokemonTeamName: String): Boolean {
        return pokemonTeamName != ""
    }

}