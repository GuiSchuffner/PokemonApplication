package com.example.pokemonapplication.home.teams.team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentNewPokemonTeamBinding
import com.example.pokemonapplication.home.teams.team.presentation.NewPokemonTeamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPokemonTeamFragment : Fragment() {

    private var _binding: FragmentNewPokemonTeamBinding? = null
    private val binding get() = _binding!!
    private val newPokemonTeamViewModel: NewPokemonTeamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPokemonTeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newPokemonTeamViewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
            binding.buttonCreateTeam.isEnabled = !it
        }
        binding.buttonCreateTeam.setOnClickListener {
            newPokemonTeamViewModel.onCreateTeamButton(binding.teamNameEdittext.text.toString())
        }
        newPokemonTeamViewModel.newTeamId.observe(viewLifecycleOwner) {
            val direction = NewPokemonTeamFragmentDirections
                .actionNewPokemonTeamFragmentToPokemonTeamActivity2(it)
            findNavController().navigate(direction)
            requireActivity().finish()
        }
    }
}
