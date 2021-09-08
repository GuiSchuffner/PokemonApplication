package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.ActivityPokemonTeamBinding

class PokemonTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonTeamBinding
    private val arguments by navArgs<PokemonTeamActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarPokemonTeam.setNavigationOnClickListener {
            onBackPressed()
        }
        findNavController(R.id.nav_pokemon_team_fragment).setGraph(
            R.navigation.team_navigation,
            arguments.toBundle()
        )
    }
}
