package com.example.pokemonapplication.home.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.FragmentFavoritesBinding
import com.example.pokemonapplication.home.favorites.presentation.FavoritesViewModel
import com.example.pokemonapplication.home.favorites.view.adapter.FavoritesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel by viewModel<FavoritesViewModel>()
    val adapter = FavoritesListAdapter(mutableListOf())

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
        binding.favoritesRecyclerView.adapter=adapter
        favoritesViewModel.getPokemonList()
        favoritesViewModel.newPokemon.observe(viewLifecycleOwner){
            adapter.addPokemon(it)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_favoritesFragment_to_searchFavoritePokemonActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.resetDataBase()
        favoritesViewModel.getPokemonList()
    }
}