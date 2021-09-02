package com.example.pokemonapplication.home.favorites.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.favorites.data.SearchPokemonTypeRepository
import com.example.pokemonapplication.home.model.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchPokemonTypeViewModel(
    private val pokemonType: String,
    private val searchPokemonTypeRepository: SearchPokemonTypeRepository
) : ViewModel() {

    private val _loading= MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init{
        searchPokemonType()
    }

    private fun searchPokemonType(){
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val type = searchPokemonTypeRepository.searchPokemonType(pokemonType)
            } catch(e: Exception){
                Log.e("aaa", e.message!!)
            }
            _loading.postValue(false)
        }
    }

}
