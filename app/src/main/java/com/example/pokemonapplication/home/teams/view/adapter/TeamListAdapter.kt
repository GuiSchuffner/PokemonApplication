package com.example.pokemonapplication.home.teams.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class TeamListAdapter(
    private var pokemonList: List<PokemonTeam>
) : RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindListItem(pokemonList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_team_item, parent, false)
        return TeamViewHolder(view)
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName =
            itemView.findViewById<MaterialTextView>(R.id.team_name_textview)
        private val numberOfPokemon =
            itemView.findViewById<MaterialTextView>(R.id.number_pokemon_value_textview)
        private val captainImage: ImageView =
            itemView.findViewById(R.id.captain_image)

        fun bindListItem(team: PokemonTeam) {
            teamName.text = team.name
            if (team.pokemonList != null) {
                numberOfPokemon.text = team.pokemonList?.size.toString()
            } else {
                numberOfPokemon.text = "0"
            }
            if (team.captainImage != null) {
                Picasso.get().load(team.captainImage).into(captainImage)
            }
        }
    }
}
