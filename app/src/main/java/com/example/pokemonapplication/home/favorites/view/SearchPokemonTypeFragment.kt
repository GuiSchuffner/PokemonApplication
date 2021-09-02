package com.example.pokemonapplication.home.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.databinding.FragmentSearchPokemonTypeBinding
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonTypeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchPokemonTypeFragment: Fragment() {
    private var _binding: FragmentSearchPokemonTypeBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<SearchPokemonTypeFragmentArgs>()
    private val searchPokemonTypeViewModel: SearchPokemonTypeViewModel by viewModel{
        parametersOf(arguments.searchText)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchPokemonTypeViewModel
    }

}
