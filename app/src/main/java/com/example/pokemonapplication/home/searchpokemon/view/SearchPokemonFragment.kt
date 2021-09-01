package com.example.pokemonapplication.home.searchpokemon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonapplication.databinding.FragmentSearchPokemonBinding
import com.example.pokemonapplication.home.searchpokemon.presentation.SearchPokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPokemonFragment : Fragment() {

    private val searchPokemonViewModel by viewModel<SearchPokemonViewModel>()
    private var _binding: FragmentSearchPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            searchPokemonViewModel.searchButtonClicked()
        }
    }
}