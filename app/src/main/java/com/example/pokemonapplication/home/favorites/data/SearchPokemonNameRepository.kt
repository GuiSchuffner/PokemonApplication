package com.example.pokemonapplication.home.favorites.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPokemonNameRepository(private val pokeApi: PokeApi) {
    suspend fun searchPokemon(pokemonName: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getPokemon(pokemonName)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }
}
