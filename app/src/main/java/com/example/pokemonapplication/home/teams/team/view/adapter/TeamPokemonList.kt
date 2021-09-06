package com.example.pokemonapplication.home.teams.team.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.home.model.Pokemon

class TeamPokemonListAdapter(
    private var pokemonList: MutableList<Pokemon>
) : RecyclerView.Adapter<TeamPokemonListAdapter.TeamPokemonItemViewHolder>() {


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TeamPokemonItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPokemonItemViewHolder {
        TODO("Not yet implemented")
    }

    class TeamPokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}