package com.example.pokemonapplication.home.teams.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.home.model.PokemonTeam
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class TeamsListAdapter(
    private var pokemonList: List<PokemonTeam>,
    private val teamListener: SelectTeamListener
) : RecyclerView.Adapter<TeamsListAdapter.TeamViewHolder>() {

    interface SelectTeamListener {
        fun teamSelectListener(teamName: String)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindListItem(pokemonList[position], teamListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_team_item, parent, false)
        return TeamViewHolder(view)
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName =
            itemView.findViewById<MaterialTextView>(R.id.team_name_textview)
        private val teamDescription: MaterialTextView =
            itemView.findViewById(R.id.team_description_textview)
        private val cardView = itemView.findViewById<MaterialCardView>(R.id.team_item_cardView)

        fun bindListItem(team: PokemonTeam, teamListener: SelectTeamListener) {
            teamName.text = team.name
            teamDescription.text = team.description
            cardView.isClickable = true
            cardView.isFocusable = true
            cardView.setOnClickListener {
                teamListener.teamSelectListener(team.name!!)
            }
        }
    }
}
