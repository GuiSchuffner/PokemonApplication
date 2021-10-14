package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.FragmentPokemonTeamBinding
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import com.example.pokemonapplication.home.teams.team.presentation.PokemonTeamViewModel
import com.example.pokemonapplication.home.teams.team.view.adapter.TeamPokemonListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class PokemonTeamFragment : Fragment(), TeamPokemonListAdapter.RemovePokemonListener {

    private var _binding: FragmentPokemonTeamBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<PokemonTeamFragmentArgs>()
    private val pokemonTeamViewModel: PokemonTeamViewModel by viewModel {
        parametersOf(arguments.teamName)
    }
    private val adapter = TeamPokemonListAdapter(
        listOf(),
        this
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonTeamBinding.inflate(layoutInflater)
        binding.pokemonRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.teamNameTextView.text = ""
        pokemonTeamViewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter.listChange(it)
        }
        binding.addPokemonButton.setOnClickListener {
            pokemonTeamViewModel.onAddPokemonButtonClicked()
        }
        pokemonTeamViewModel.addPokemon.observe(viewLifecycleOwner) {
            val direction =
                PokemonTeamFragmentDirections.actionPokemonTeamFragmentToSearchPokemonActivity2(
                    SearchPokemonActivity.SEARCH_FOR_TEAM,
                    arguments.teamName
                )
            findNavController().navigate(direction)
        }
        pokemonTeamViewModel.error.observe(viewLifecycleOwner) {
            context?.let { context ->
                MaterialAlertDialogBuilder(context)
                    .setMessage(it)
                    .setPositiveButton(
                        getString(R.string.ok)
                    ) { _, _ -> }
                    .show()
            }
        }

    }

    override fun removePokemonListener(itemKey: String) {
        pokemonTeamViewModel.removePokemon(itemKey)
    }
}
