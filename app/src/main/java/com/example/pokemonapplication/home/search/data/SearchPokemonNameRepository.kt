package com.example.pokemonapplication.home.search.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SearchPokemonNameRepository(private val pokeApi: PokeApi) {

    suspend fun addPokemonToFavorites(pokemonId: Int){
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if(userId != null){
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            val pokemonList = databaseReference.child("favorites")
                .get().await().getValue<List<Int>>()
            if (pokemonList != null) {
                val newList = pokemonList.toMutableList()
                newList.add(pokemonId)
                databaseReference.child("favorites").setValue(newList).await()
            } else {
                databaseReference.child("favorites").setValue(listOf(pokemonId)).await()
            }
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

    suspend fun addPokemonToTeam(pokemonId: Int, teamId: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            val teamList = databaseReference.child("teams")
                .get().await().getValue<List<PokemonTeam>>()
            if (teamList != null && teamList.size > teamId) {
                if (teamList[teamId].pokemonList != null) {
                    val newList = teamList[teamId].pokemonList!!.toMutableList()
                    newList.add(pokemonId)
                    teamList[teamId].pokemonList = newList
                } else {
                    teamList[teamId].pokemonList = listOf(pokemonId)
                }
                databaseReference.child("teams").setValue(teamList).await()
            }
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

    suspend fun searchPokemon(pokemonName: String): Pokemon? {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getPokemon(pokemonName)
            when {
                response.isSuccessful -> {
                    return@withContext response.body()
                }
                response.code() == POKEMON_NOT_FOUND -> {
                    return@withContext null
                }
                else -> {
                    throw Exception("Não foi possível realizar o acesso")
                }
            }
        }
    }

    companion object {
        private const val POKEMON_NOT_FOUND = 404
    }
}
