package com.example.pokemonapplication.home.favorites.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.favorites.data.SearchPokemonNameRepository
import com.example.pokemonapplication.home.model.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPokemonNameViewModel (
    private val pokemonName: String,
    private val searchPokemonNameRepository: SearchPokemonNameRepository
) : ViewModel() {
    private val _pokemon= MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon
    private val _loading= MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _isFavButtonEnable= MutableLiveData<Boolean>()
    val isFavButtonEnable: LiveData<Boolean> = _isFavButtonEnable

    init{
        _isFavButtonEnable.postValue(false)
        searchPokemonName()
    }

    fun onFavoriteButtonClicked(){
        searchPokemonNameRepository.addPokemonToFavorites(_pokemon.value!!.id)
    }

    private fun searchPokemonName() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                _pokemon.postValue(searchPokemonNameRepository.searchPokemon(pokemonName))
                _isFavButtonEnable.postValue(true)
            } catch(e: Exception){
                Log.e("aaa", e.message!!)
            }
            _loading.postValue(false)
        }
    }
}