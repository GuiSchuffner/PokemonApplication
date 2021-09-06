package com.example.pokemonapplication.home.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PokemonTeam(var name: String? = null, var pokemonList: List<Int>? = null)
