package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapplication.databinding.ActivityPokemonTeamBinding

class PokemonTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
