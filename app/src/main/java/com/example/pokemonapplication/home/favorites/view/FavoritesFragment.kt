package com.example.pokemonapplication.home.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.databinding.FragmentFavoritesBinding
import com.example.pokemonapplication.home.favorites.presentation.FavoritesViewModel
import com.example.pokemonapplication.home.favorites.view.adapter.FavoritesListAdapter
import com.example.pokemonapplication.home.search.view.SearchPokemonActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel by viewModel<FavoritesViewModel>()
    private val adapter = FavoritesListAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesRecyclerView.adapter = adapter
        favoritesViewModel.getPokemonList()
        favoritesViewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        favoritesViewModel.isFavListEmpty.observe(viewLifecycleOwner) {
            binding.favoritesRecyclerView.isVisible = !it
            binding.favoritesIsEmptyConstraint.isVisible = it
        }
        favoritesViewModel.newPokemon.observe(viewLifecycleOwner) {
            adapter.addPokemon(it)
        }
        binding.floatingActionButton.setOnClickListener {
            val direction =
                FavoritesFragmentDirections.actionFavoritesFragmentToSearchPokemonActivity(
                    SearchPokemonActivity.SEARCH_FOR_FAVORITES
                )
            findNavController().navigate(direction)
        }
    }


    override fun onResume() {
        super.onResume()
        favoritesViewModel.getPokemonList()
    }
}