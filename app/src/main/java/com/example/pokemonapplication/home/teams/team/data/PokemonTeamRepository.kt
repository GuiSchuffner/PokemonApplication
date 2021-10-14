package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.model.PokemonListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PokemonTeamRepository(private val pokeApi: PokeApi) {

    @ExperimentalCoroutinesApi
    fun listenPokemonTeam(teamId: String) = callbackFlow<Result<List<PokemonListItem>?>> {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("pokemonList").child(teamId)
            val teamListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val teamList = mutableListOf<PokemonListItem>()
                    for (children in snapshot.children) {
                        teamList.add(children.getValue<PokemonListItem>()!!)
                    }
                    this@callbackFlow.sendBlocking(
                        Result.success(
                            teamList
                        )
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.sendBlocking(Result.failure(error.toException()))
                }
            }
            databaseReference.addValueEventListener(teamListener)
            awaitClose {
                databaseReference.removeEventListener(teamListener)
            }
        }
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

    suspend fun removePokemon(teamId: String, pokemonKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("pokemonList").child(teamId)
                .child(pokemonKey)
            databaseReference.setValue(null).addOnFailureListener {
                throw it
            }.await()
        }
    }

}
