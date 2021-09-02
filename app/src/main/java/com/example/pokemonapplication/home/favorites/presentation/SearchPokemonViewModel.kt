package com.example.pokemonapplication.home.favorites.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.favorites.data.SearchPokemonRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPokemonViewModel() : ViewModel() {

    private var searchText: String = ""
    private var searchType: String = SEARCH_POKEMON_NAME

    fun onSearchTextChanged (text: String) {
        searchText=text
    }

    fun onSearchTypeChanged (text: String) {
       searchType=text
    }

    companion object{
        private const val SEARCH_POKEMON_NAME="Nome"
        private const val SEARCH_POKEMON_TYPE="Tipo"
    }
}
