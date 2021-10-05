package com.example.pokemonapplication.home.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class PokemonTeam(
    var name: String? = null,
    var description: String? = null,
    var id: String? = null
) : Serializable
