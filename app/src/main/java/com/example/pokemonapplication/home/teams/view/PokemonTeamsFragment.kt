package com.example.pokemonapplication.home.teams.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentPokemonTeamsBinding

class PokemonTeamsFragment : Fragment() {

    private var _binding: FragmentPokemonTeamsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonTeamsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newTeamButton.setOnClickListener {
            findNavController().navigate(
                PokemonTeamsFragmentDirections.actionPokemonTeamsFragmentToPokemonTeamActivity()
            )
        }
    }
}
