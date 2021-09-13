package com.example.pokemonapplication.home.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.TypePokemon
import com.example.pokemonapplication.home.search.data.SearchPokemonTypeRepository
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import com.example.pokemonapplication.home.util.SingleLiveEvent
import kotlinx.coroutines.launch

class SearchPokemonTypeViewModel(
    private val searchIntent: Int,
    private val teamId: Int,
    private val pokemonType: String,
    private val searchPokemonTypeRepository: SearchPokemonTypeRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _addPokemon = MutableLiveData<Pokemon>()
    val addPokemon: LiveData<Pokemon> = _addPokemon

    private val _addToFirebaseFinished = SingleLiveEvent<Unit>()
    val addToFirebaseFinished: LiveData<Unit> = _addToFirebaseFinished

    private var addingToFirebase = false

    init {
        searchPokemonType()
    }

    private fun searchPokemonType() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val type = searchPokemonTypeRepository.searchPokemonType(pokemonType)
                searchPokemonList(type.pokemon)
            } catch (e: Exception) {
                Log.e("aaa", e.message!!)
            }
            if (!addingToFirebase) {
                _loading.postValue(false)
            }
        }
    }

    fun onAddButtonClicked(pokemonId: Int) {
        _loading.postValue(true)
        addingToFirebase = true
        viewModelScope.launch {
            try {
                if (searchIntent == SearchPokemonActivity.SEARCH_FOR_FAVORITES) {
                    searchPokemonTypeRepository.addPokemonToFavorites(pokemonId)
                } else {
                    searchPokemonTypeRepository.addPokemonToTeam(
                        pokemonId,
                        teamId
                    )
                }
                _addToFirebaseFinished.postValue(Unit)
            } catch (e: Exception) {
                Log.e("aaa", e.message!!)
            }
            addingToFirebase = false
            _loading.postValue(false)
        }

    }

    private suspend fun searchPokemonList(typePokemonList: List<TypePokemon>) {
        val newList = mutableListOf<Pokemon>()
        for (i in typePokemonList) {
            val pokemon = searchPokemonTypeRepository.searchPokemon(i.pokemon.name)
            if (pokemon != null) {
                newList.add(pokemon)
                _addPokemon.postValue(pokemon)
            }
        }
    }

}
