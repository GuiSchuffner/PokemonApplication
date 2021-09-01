package com.example.pokemonapplication.home.searchpokemon.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.searchpokemon.data.SearchPokemonRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPokemonViewModel(
    private val searchPokemonRepository: SearchPokemonRepository
    ) : ViewModel() {

    fun searchButtonClicked() {
        viewModelScope.launch {
            try {
                searchPokemonRepository.searchPokemon()
            } catch(e: Exception){
                Log.e("aaa", e.message!!)
            }
        }
    }
}
