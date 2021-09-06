package com.example.pokemonapplication.home.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.search.data.SearchPokemonTypeRepository
import kotlinx.coroutines.launch

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
