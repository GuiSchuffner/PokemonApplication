package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.databinding.FragmentPokemonTeamBinding
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import com.example.pokemonapplication.home.teams.team.presentation.PokemonTeamViewModel
import com.example.pokemonapplication.home.teams.team.view.adapter.TeamPokemonListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonTeamFragment : Fragment() {

    private var _binding: FragmentPokemonTeamBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<PokemonTeamFragmentArgs>()
    private val pokemonTeamViewModel: PokemonTeamViewModel by viewModel {
        parametersOf(arguments.teamId)
    }

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
        pokemonTeamViewModel.isAddButtonEnable.observe(viewLifecycleOwner) {
            binding.addPokemonButton.isEnabled = it
        }
        pokemonTeamViewModel.pokemonTeam.observe(viewLifecycleOwner) {
            binding.pokemonRecyclerView.adapter = TeamPokemonListAdapter(it)
        }
        binding.addPokemonButton.setOnClickListener {
            val direction =
                PokemonTeamFragmentDirections.actionPokemonTeamFragmentToSearchPokemonActivity2(
                    SearchPokemonActivity.SEARCH_FOR_TEAM,
                    arguments.teamId
                )
            findNavController().navigate(direction)
        }
    }

    override fun onResume() {
        super.onResume()
        pokemonTeamViewModel.getPokemonTeam()
    }
}
