package com.example.pokemonapplication.home.teams.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentPokemonTeamsBinding
import com.example.pokemonapplication.home.teams.presentation.PokemonTeamsViewModel
import com.example.pokemonapplication.home.teams.view.adapter.TeamListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonTeamsFragment : Fragment() {

    private var _binding: FragmentPokemonTeamsBinding? = null
    private val binding get() = _binding!!
    private val pokemonTeamsViewModel: PokemonTeamsViewModel by viewModel()

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
        pokemonTeamsViewModel.teamsList.observe(viewLifecycleOwner) {
            binding.pokemonTeamRecyclerView.adapter = TeamListAdapter(it)
        }
        binding.newTeamButton.setOnClickListener {
            findNavController().navigate(
                PokemonTeamsFragmentDirections.actionPokemonTeamsFragmentToNewPokemonTeamActivity()
            )
        }
    }
}
