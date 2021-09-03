package com.example.pokemonapplication.home.favorites.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPokemonTypeRepository(private val pokeApi: PokeApi) {

    suspend fun searchPokemonType(pokemonType: String): Type {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getType(pokemonType)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }
}
