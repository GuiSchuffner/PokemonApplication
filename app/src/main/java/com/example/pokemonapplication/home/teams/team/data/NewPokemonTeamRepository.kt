package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class NewPokemonTeamRepository {
    suspend fun createTeam(pokemonTeam: PokemonTeam): String {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("teamsList")
            val pokemonList = databaseReference.get().await().getValue<List<PokemonTeam>>()
            if (pokemonList != null) {
                pokemonList.forEach {
                    if (it.name == pokemonTeam.name) throw Exception("Nome ja utilizado")
                }
                val list = pokemonList.toMutableList()
                list.add(pokemonTeam)
                databaseReference.setValue(
                    list
                ).await()
            } else {
                databaseReference.setValue(listOf(pokemonTeam)).await()
            }
            return pokemonTeam.name!!
        } else {
            throw Exception("Usuário não encontrado")
        }
    }
}
