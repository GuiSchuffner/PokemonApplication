package com.example.pokemonapplication.home.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.search.data.SearchPokemonNameRepository
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import kotlinx.coroutines.launch

class SearchPokemonNameViewModel(
    private val pokemonName: String,
    private val searchIntent: Int,
    private val teamId: Int,
    private val searchPokemonNameRepository: SearchPokemonNameRepository
) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _isPokemonFound = MutableLiveData<Boolean>()
    val isPokemonFound: LiveData<Boolean> = _isPokemonFound
    private val _isAddButtonEnable = MutableLiveData<Boolean>()
    val isAddButtonEnable: LiveData<Boolean> = _isAddButtonEnable
    private val _addToFavoritesFinished = MutableLiveData<Unit>()
    val addToFavoritesFinished: LiveData<Unit> = _addToFavoritesFinished

    init {
        _isAddButtonEnable.postValue(false)
        searchPokemonName()
    }

    fun onAddButtonClicked() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                if (searchIntent == SearchPokemonActivity.SEARCH_FOR_FAVORITES) {
                    searchPokemonNameRepository.addPokemonToFavorites(_pokemon.value!!.id)
                } else {
                    searchPokemonNameRepository.addPokemonToTeam(
                        _pokemon.value!!.id,
                        teamId
                    )
                }
                _addToFavoritesFinished.postValue(Unit)
            } catch (e: Exception) {
                Log.e("aaa", e.message!!)
            }
            _loading.postValue(false)
        }

    }

    private fun searchPokemonName() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = searchPokemonNameRepository.searchPokemon(pokemonName.lowercase())
                if (response == null) {
                    _isPokemonFound.postValue(false)
                } else {
                    _pokemon.postValue(response)
                    _isPokemonFound.postValue(true)
                }
                _isAddButtonEnable.postValue(true)
            } catch(e: Exception){
                Log.e("aaa", e.message!!)
            }
            _loading.postValue(false)
        }
    }
}