package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PokemonTeamRepository(private val pokeApi: PokeApi) {

    suspend fun getTeam(teamId: Int): PokemonTeam? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            return databaseReference.child("teams")
                .get().await().getValue<List<PokemonTeam>>()?.get(teamId)
        }
        return null
    }

    suspend fun searchPokemon(pokemonId: Int): Pokemon {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getPokemon(pokemonId)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }

    suspend fun removePokemon(teamId: Int, pokemonId: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            val pokemonList = databaseReference.child("teams")
                .get().await().getValue<List<PokemonTeam>>()
            if (pokemonList != null) {
                val newList = pokemonList[teamId].pokemonList!!.toMutableList()
                newList.remove(pokemonId)
                pokemonList[teamId].pokemonList = newList
                databaseReference.child("teams").setValue(
                    pokemonList
                ).await()
            } else {
                throw Exception("Teams not found")
            }
        } else {
            throw Exception("User not found")
        }
    }

}
