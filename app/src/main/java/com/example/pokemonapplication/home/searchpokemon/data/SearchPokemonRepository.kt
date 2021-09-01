package com.example.pokemonapplication.home.searchpokemon.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPokemonRepository(private val pokeApi: PokeApi) {
    suspend fun searchPokemon(): Pokemon {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getPokemon("butterfree")
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }
}
