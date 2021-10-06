package com.example.pokemonapplication.home.teams.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentPokemonTeamsBinding
import com.example.pokemonapplication.home.teams.presentation.PokemonTeamsViewModel
import com.example.pokemonapplication.home.teams.view.adapter.TeamsListAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonTeamsFragment : Fragment(), TeamsListAdapter.SelectTeamListener {

    private var _binding: FragmentPokemonTeamsBinding? = null
    private val binding get() = _binding!!

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private val pokemonTeamsViewModel: PokemonTeamsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonTeamsBinding.inflate(layoutInflater)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonTeamsViewModel.teamsList.observe(viewLifecycleOwner) {
            binding.pokemonTeamRecyclerView.adapter = TeamsListAdapter(it, this)
        }
        pokemonTeamsViewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        pokemonTeamsViewModel.isTeamsListEmpty.observe(viewLifecycleOwner) {
            binding.pokemonTeamRecyclerView.isVisible = !it
            binding.teamsListIsEmptyConstraint.isVisible = it
        }
        binding.newTeamButton.setOnClickListener {
            findNavController().navigate(
                PokemonTeamsFragmentDirections.actionPokemonTeamsFragmentToNewPokemonTeamActivity()
            )
        }
    }

    override fun teamSelectListener(teamId: String) {
        findNavController().navigate(
            PokemonTeamsFragmentDirections.actionPokemonTeamsFragmentToPokemonTeamActivity(teamId)
        )
    }

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun removeTeamListener(teamId: String) {
        pokemonTeamsViewModel.removeTeam(teamId)
    }
}
