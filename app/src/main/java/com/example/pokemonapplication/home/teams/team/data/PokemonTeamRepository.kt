package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PokemonTeamRepository(private val pokeApi: PokeApi) {

    suspend fun getTeam(teamId: String): List<Int>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("pokemonList").child(teamId)
            return databaseReference
                .get().await().getValue<List<Int>>()
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

    suspend fun removePokemon(teamName: String, pokemonId: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("pokemonList").child(teamName)
            val pokemonList = databaseReference
                .get().await().getValue<List<Int>>()
            if (pokemonList != null) {
                val newList = pokemonList.toMutableList()
                newList.remove(pokemonId)
                databaseReference.setValue(
                    newList
                ).await()
            } else {
                throw Exception("Teams not found")
            }
        } else {
            throw Exception("User not found")
        }
    }

}
