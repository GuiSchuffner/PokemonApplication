package com.example.pokemonapplication.home.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokemonapplication.R
import com.example.pokemonapplication.databinding.FragmentSearchPokemonNameBinding
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.search.presentation.SearchPokemonNameViewModel
import com.example.pokemonapplication.home.util.PokemonHelper
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SearchPokemonNameFragment: Fragment() {

    private var _binding: FragmentSearchPokemonNameBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<SearchPokemonNameFragmentArgs>()

    private val searchNamePokemonViewModel: SearchPokemonNameViewModel by viewModel {
        parametersOf(arguments.searchText, arguments.searchIntent, arguments.teamId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchPokemonNameBinding.inflate(layoutInflater)
        if (arguments.searchIntent == SearchPokemonActivity.SEARCH_FOR_FAVORITES) {
            binding.addButton.text = getString(R.string.adicionar_aos_favoritos)
        } else {
            binding.addButton.text = getString(R.string.adicionar_ao_time)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        searchNamePokemonViewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        searchNamePokemonViewModel.isAddButtonEnable.observe(viewLifecycleOwner) {
            binding.addButton.isEnabled = it
        }
        searchNamePokemonViewModel.isPokemonFound.observe(viewLifecycleOwner) {
            binding.pokemonNotFoundConstraint.isVisible = !it
            binding.pokemonInfoCard.isVisible = it
        }
        searchNamePokemonViewModel.addToFavoritesFinished.observe(viewLifecycleOwner) {
            requireActivity().finish()
        }
        searchNamePokemonViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.pokemonName.text = it.name
            Picasso.get().load(it.sprites.front_default).into(binding.pokemonImage)
            binding.pokemonHeightValueText.text = it.height.toString()
            binding.pokemonWeightValueText.text = it.weight.toString()
            PokemonHelper.bindPokemonTypeColorCardView(
                it.types[0].type.name,
                binding.pokemonInfoCardConstraint,
                view
            )
            bindPokemonTypes(it)
        }
        binding.addButton.setOnClickListener {
            searchNamePokemonViewModel.onAddButtonClicked()
        }
    }

    private fun bindPokemonTypes(pokemon: Pokemon) {
        PokemonHelper.bindPokemonTypeImageView(
            pokemon.types[0].type.name, binding.pokemonTypeImage1
        )
        if(pokemon.types.size==2) {
            PokemonHelper.bindPokemonTypeImageView(
                pokemon.types[1].type.name, binding.pokemonTypeImage2
            )
        }
    }

}
