package com.example.pokemonapplication.home.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapplication.home.util.SingleLiveEvent

class SearchPokemonViewModel : ViewModel() {

    private var searchText: String = ""
    private var searchType: String = SEARCH_POKEMON_NAME
    private val _isSearchButtonEnable = MutableLiveData<Boolean>()
    val isSearchButtonEnable: LiveData<Boolean> = _isSearchButtonEnable
    private val _searchByName = SingleLiveEvent<Unit>()
    val searchByName: LiveData<Unit> = _searchByName
    private val _searchByType = SingleLiveEvent<Unit>()
    val searchByType: LiveData<Unit> = _searchByType

    init {
        _isSearchButtonEnable.postValue(false)
    }

    fun onSearchTextChanged (text: String) {
        searchText=text
        _isSearchButtonEnable.postValue(searchText!="")
    }

    fun onSearchTypeChanged (text: String) {
       searchType=text
    }

    fun onSearchButtonClicked() {
        if(searchType == SEARCH_POKEMON_NAME)
            _searchByName.postValue(Unit)
        else
            _searchByType.postValue(Unit)
    }

    companion object{
        private const val SEARCH_POKEMON_NAME="Nome"
        private const val SEARCH_POKEMON_TYPE="Tipo"
    }
}
