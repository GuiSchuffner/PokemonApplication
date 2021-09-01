package com.example.pokemonapplication.home.searchpokemon.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.databinding.FragmentSearchPokemonNameBinding
import com.example.pokemonapplication.home.searchpokemon.presentation.SearchPokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPokemonNameFragment: Fragment() {
    private var _binding: FragmentSearchPokemonNameBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<SearchPokemonNameFragmentArgs>()
    private val searchPokemonViewModel by viewModel<SearchPokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonNameBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchPokemonViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.pokemonName.text=it.name
            binding.pokemonHeightValueText.text=it.height.toString()
            binding.pokemonWeightValueText.text=it.weight.toString()
        }
        searchPokemonViewModel.searchButtonClicked(arguments.searchText)
    }
}