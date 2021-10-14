package com.example.pokemonapplication.home.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PokemonListItem(
    val key: String? = null,
    val pokemonId: Int? = null
)
