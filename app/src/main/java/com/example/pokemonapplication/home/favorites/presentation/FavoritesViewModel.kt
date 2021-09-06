package com.example.pokemonapplication.home.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.favorites.data.FavoritesRepository
import com.example.pokemonapplication.home.model.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
    ): ViewModel() {

    private val _loading= MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _newPokemon =  MutableLiveData<Pokemon>()
    val newPokemon: LiveData<Pokemon> = _newPokemon


    fun getPokemonList(){
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val pokemonList = favoritesRepository.getFavorites()
                searchPokemon(pokemonList)
            } catch (e: Exception){

            }
            _loading.postValue(false)
        }
    }

    private suspend fun searchPokemon(pokemonList: List<Int>?){
        if(pokemonList!=null){
            for(id in pokemonList){
                val pokemon = favoritesRepository.searchPokemon(id)
                _newPokemon.postValue(pokemon)
            }
        }
    }
}
