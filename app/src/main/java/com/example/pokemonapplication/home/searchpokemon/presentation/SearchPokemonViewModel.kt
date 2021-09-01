package com.example.pokemonapplication.home.searchpokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.searchpokemon.data.SearchPokemonRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPokemonViewModel(
    private val searchPokemonRepository: SearchPokemonRepository
    ) : ViewModel() {

    private val _pokemon= MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon
    private var searchText: String = ""
    private var searchType: String = SEARCH_POKEMON_NAME

    fun onSearchTextChanged (text: String) {
        searchText=text
    }

    fun onSearchTypeChanged (text: String) {
       searchType=text
    }

    fun searchButtonClicked(pokemonName: String) {
        viewModelScope.launch {
            try {
                _pokemon.postValue(searchPokemonRepository.searchPokemon(pokemonName))
            } catch(e: Exception){
                Log.e("aaa", e.message!!)
            }
        }
    }

    companion object{
        private const val SEARCH_POKEMON_NAME="Nome"
        private const val SEARCH_POKEMON_TYPE="Tipo"
    }
}
