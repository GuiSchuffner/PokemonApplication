package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapplication.databinding.ActivityNewPokemonTeamBinding

class NewPokemonTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPokemonTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
