package com.example.pokemonapplication.home.teams.data

import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow

class PokemonTeamsRepository {

    @ExperimentalCoroutinesApi
    fun getTeams() = callbackFlow<Result<List<PokemonTeam>?>> {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val teamsListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.sendBlocking(Result.failure(error.toException()))
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val teamList = mutableListOf<PokemonTeam>()
                    for (children in dataSnapshot.children) {
                        teamList.add(children.getValue<PokemonTeam>()!!)
                    }
                    this@callbackFlow.sendBlocking(
                        Result.success(
                            teamList.toList()
                        )
                    )
                }
            }
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(userId).child("teams").child("teamsList")
            databaseReference.keepSynced(true)
            databaseReference.addValueEventListener(teamsListener)
            awaitClose {
                databaseReference.removeEventListener(teamsListener)
            }
        }
    }

    fun removeTeam(teamId: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val empty = mapOf(
                "team" to null
            )
            val childUpdates = hashMapOf<String, Any>(
                "users/$userId/teams/teamsList/$teamId" to empty,
                "users/$userId/teams/pokemonList/$teamId" to empty
            )
            val databaseReference = FirebaseDatabase.getInstance().reference
            databaseReference.updateChildren(childUpdates)
        }
    }
}
