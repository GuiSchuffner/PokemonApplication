package com.example.pokemonapplication.home.favorites.data

import com.example.pokemonapplication.home.api.PokeApi
import com.example.pokemonapplication.home.model.Pokemon
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
            /*databaseReference.child("favorites").get().addOnSuccessListener {
                val favoritesIds = addToFavList(pokemonId, it.getValue<List<Int>>())
                databaseReference.child("favorites").setValue(favoritesIds)
            }.addOnFailureListener {
                throw it
            }*/
            val pokemonList = databaseReference.child("favorites")
                .get().await().getValue<List<Int>>()
            if(pokemonList != null){
                val newList = pokemonList.toMutableList()
                newList.add(pokemonId)
                databaseReference.child("favorites").setValue(newList)
            }
            else{
                databaseReference.child("favorites").setValue(listOf(pokemonId))
            }
        }
    }

    private fun addToFavList(pokemonId: Int, ids: List<Int>?): List<Int>{
        var favoritesIds : List<Int> = listOf(pokemonId)
        if(ids != null && ids.isNotEmpty()){
            val newList: MutableList<Int> = ids.toMutableList()
            if(!newList.contains(pokemonId)) {
                newList.add(pokemonId)
            }
            favoritesIds = newList.toList()
        }
        return favoritesIds
    }

    suspend fun searchPokemon(pokemonName: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val response = pokeApi.getPokemon(pokemonName)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }
}
