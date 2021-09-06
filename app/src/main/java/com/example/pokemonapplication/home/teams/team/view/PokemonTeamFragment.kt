package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentPokemonTeamBinding
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import com.example.pokemonapplication.home.teams.team.presentation.PokemonTeamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonTeamFragment : Fragment() {
    private var _binding: FragmentPokemonTeamBinding? = null
    private val binding get() = _binding!!
    private val pokemonTeamViewModel by viewModel<PokemonTeamViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonTeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonTeamViewModel.getPokemonTeam()
        pokemonTeamViewModel.isAddButtonEnable.observe(viewLifecycleOwner) {
            binding.addPokemonButton.isEnabled = it
        }
        binding.addPokemonButton.setOnClickListener {
            val direction =
                PokemonTeamFragmentDirections.actionPokemonTeamFragmentToSearchPokemonActivity2(
                    SearchPokemonActivity.SEARCH_FOR_TEAM,
                    pokemonTeamViewModel.teamId.value!!
                )
            findNavController().navigate(direction)
        }
    }
}
