package com.example.pokemonapplication.home.teams.team.data

import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class NewPokemonTeamRepository {
    suspend fun createTeam(pokemonTeam: PokemonTeam): String {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("teamsList")
            val key = databaseReference.push().key
            pokemonTeam.id = key
            databaseReference.child(key!!).setValue(pokemonTeam).await()
            return pokemonTeam.id!!
        } else {
            throw Exception("Usuário não encontrado")
        }
    }
}
