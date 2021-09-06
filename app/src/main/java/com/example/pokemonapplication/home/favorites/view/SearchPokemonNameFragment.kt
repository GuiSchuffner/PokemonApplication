package com.example.pokemonapplication.home.favorites.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.FragmentSearchPokemonNameBinding
import com.example.pokemonapplication.home.favorites.presentation.SearchPokemonNameViewModel
import com.example.pokemonapplication.home.favorites.view.util.PokemonBindHelper
import com.example.pokemonapplication.home.model.Pokemon
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SearchPokemonNameFragment: Fragment() {

    private var _binding: FragmentSearchPokemonNameBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<SearchPokemonNameFragmentArgs>()

    private val searchNamePokemonViewModel: SearchPokemonNameViewModel by viewModel {
        parametersOf(arguments.searchText)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonNameBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        searchNamePokemonViewModel.loading.observe(viewLifecycleOwner){
            binding.loading.isVisible=it
        }
        searchNamePokemonViewModel.isFavButtonEnable.observe(viewLifecycleOwner) {
            binding.addToFavorites.isEnabled=it
        }
        searchNamePokemonViewModel.addToFavoritesFinished.observe(viewLifecycleOwner){
            requireActivity().finish()
        }
        searchNamePokemonViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.pokemonName.text=it.name
            Picasso.get().load(it.sprites.front_default).into(binding.pokemonImage)
            binding.pokemonHeightValueText.text=it.height.toString()
            binding.pokemonWeightValueText.text=it.weight.toString()
            bindPokemonTypes(it)
        }
        binding.addToFavorites.setOnClickListener {
            searchNamePokemonViewModel.onFavoriteButtonClicked()
        }
    }

    private fun bindPokemonTypes(pokemon: Pokemon) {
        PokemonBindHelper.bindPokemonTypeImageView(
            pokemon.types[0].type.name, binding.pokemonTypeImage1
        )
        if(pokemon.types.size==2) {
            PokemonBindHelper.bindPokemonTypeImageView(
                pokemon.types[1].type.name, binding.pokemonTypeImage2
            )
        }
    }

}
