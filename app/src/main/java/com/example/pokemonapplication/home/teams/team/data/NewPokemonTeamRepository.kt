package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class NewPokemonTeamRepository {
    suspend fun createTeam(teamName: String): Int {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            val pokemonList = databaseReference.child("teams")
                .get().await().getValue<List<PokemonTeam>>()
            return if (pokemonList != null) {
                val list = pokemonList.toMutableList()
                list.add(PokemonTeam(teamName, listOf()))
                databaseReference.child("teams").setValue(
                    list
                ).await()
                list.size - 1
            } else {
                val team = PokemonTeam(teamName, listOf())
                databaseReference.child("teams").setValue(listOf(team)).await()
                0
            }
        } else {
            throw Exception("Usuário não encontrado")
        }
    }
}
