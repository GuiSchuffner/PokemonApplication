package com.example.pokemonapplication.home.teams.data

import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class PokemonTeamsRepository {

    suspend fun getTeam(): List<PokemonTeam>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            return databaseReference.child("teams")
                .get().await().getValue<List<PokemonTeam>>()
        }
        return null
    }

}
