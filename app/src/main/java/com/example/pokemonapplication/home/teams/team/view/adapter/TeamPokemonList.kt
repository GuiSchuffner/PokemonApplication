package com.example.pokemonapplication.home.teams.team.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.util.PokemonHelper
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class TeamPokemonListAdapter(
    private var pokemonList: List<Pokemon>
) : RecyclerView.Adapter<TeamPokemonListAdapter.TeamPokemonItemViewHolder>() {


    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: TeamPokemonItemViewHolder, position: Int) {
        holder.bindListItem(pokemonList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPokemonItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_team_pokemon_item, parent, false)
        return TeamPokemonItemViewHolder(view)
    }

    class TeamPokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pokemonName =
            itemView.findViewById<MaterialTextView>(R.id.pokemon_name_textview)
        private val pokemonImage: ImageView =
            itemView.findViewById(R.id.list_item_pokemon_image)
        private val pokemonType1: ImageView =
            itemView.findViewById(R.id.team_pokemon_type_image1)
        private val pokemonType2: ImageView =
            itemView.findViewById(R.id.list_item_pokemon_type_image2)

        fun bindListItem(pokemon: Pokemon) {
            pokemonName.text = pokemon.name
            Picasso.get().load(pokemon.sprites.front_default).into(pokemonImage)
            PokemonHelper.bindPokemonTypeImageView(
                pokemon.types[0].type.name, pokemonType1
            )
            if (pokemon.types.size == 2) {
                pokemonType2.isVisible = true
                PokemonHelper.bindPokemonTypeImageView(
                    pokemon.types[1].type.name, pokemonType2
                )
            } else {
                pokemonType2.isVisible = false
            }
        }
    }

}
