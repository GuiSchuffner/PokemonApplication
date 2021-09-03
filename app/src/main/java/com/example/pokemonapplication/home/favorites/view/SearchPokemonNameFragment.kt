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

        searchNamePokemonViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.pokemonName.text=it.name
            Picasso.get().load(it.sprites.front_default).into(binding.pokemonImage)
            binding.pokemonHeightValueText.text=it.height.toString()
            binding.pokemonWeightValueText.text=it.weight.toString()
            bindPokemonTypes(it)
        }
        binding.addToFavorites.setOnClickListener {
            searchNamePokemonViewModel.onFavoriteButtonClicked()
            requireActivity().finish()
        }
    }

    private fun bindPokemonTypes(pokemon: Pokemon) {
        when (pokemon.types[0].type.name) {
            FIRE_TYPE ->
                Picasso.get().load(R.drawable.type_fire).into(binding.pokemonTypeImage1)
            FAIRY_TYPE ->
                Picasso.get().load(R.drawable.type_fairy).into(binding.pokemonTypeImage1)
            NORMAL_TYPE ->
                Picasso.get().load(R.drawable.type_normal).into(binding.pokemonTypeImage1)
            GRASS_TYPE ->
                Picasso.get().load(R.drawable.type_grass).into(binding.pokemonTypeImage1)
            GHOST_TYPE ->
                Picasso.get().load(R.drawable.type_ghost).into(binding.pokemonTypeImage1)
            WATER_TYPE ->
                Picasso.get().load(R.drawable.type_water).into(binding.pokemonTypeImage1)
            ICE_TYPE ->
                Picasso.get().load(R.drawable.type_ice).into(binding.pokemonTypeImage1)
            ROCK_TYPE ->
                Picasso.get().load(R.drawable.type_rock).into(binding.pokemonTypeImage1)
            GROUND_TYPE ->
                Picasso.get().load(R.drawable.type_ground).into(binding.pokemonTypeImage1)
            FLYING_TYPE ->
                Picasso.get().load(R.drawable.type_flying).into(binding.pokemonTypeImage1)
            FIGHTING_TYPE ->
                Picasso.get().load(R.drawable.type_fighting).into(binding.pokemonTypeImage1)
            PSYCHIC_TYPE ->
                Picasso.get().load(R.drawable.type_psychic).into(binding.pokemonTypeImage1)
            BUG_TYPE ->
                Picasso.get().load(R.drawable.type_bug).into(binding.pokemonTypeImage1)
            DRAGON_TYPE ->
                Picasso.get().load(R.drawable.type_dragon).into(binding.pokemonTypeImage1)
            POISON_TYPE ->
                Picasso.get().load(R.drawable.type_poison).into(binding.pokemonTypeImage1)
            DARK_TYPE ->
                Picasso.get().load(R.drawable.type_dark).into(binding.pokemonTypeImage1)
            ELECTRIC_TYPE ->
                Picasso.get().load(R.drawable.type_electric).into(binding.pokemonTypeImage1)
            STEEL_TYPE ->
                Picasso.get().load(R.drawable.type_steel).into(binding.pokemonTypeImage1)
        }
        if(pokemon.types.size==2) {
            when (pokemon.types[1].type.name) {
                FIRE_TYPE ->
                    Picasso.get().load(R.drawable.type_fire).into(binding.pokemonTypeImage2)
                FAIRY_TYPE ->
                    Picasso.get().load(R.drawable.type_fairy).into(binding.pokemonTypeImage2)
                NORMAL_TYPE ->
                    Picasso.get().load(R.drawable.type_normal).into(binding.pokemonTypeImage2)
                GRASS_TYPE ->
                    Picasso.get().load(R.drawable.type_grass).into(binding.pokemonTypeImage2)
                GHOST_TYPE ->
                    Picasso.get().load(R.drawable.type_ghost).into(binding.pokemonTypeImage2)
                WATER_TYPE ->
                    Picasso.get().load(R.drawable.type_water).into(binding.pokemonTypeImage2)
                ICE_TYPE ->
                    Picasso.get().load(R.drawable.type_ice).into(binding.pokemonTypeImage2)
                ROCK_TYPE ->
                    Picasso.get().load(R.drawable.type_rock).into(binding.pokemonTypeImage2)
                GROUND_TYPE ->
                    Picasso.get().load(R.drawable.type_ground).into(binding.pokemonTypeImage2)
                FLYING_TYPE ->
                    Picasso.get().load(R.drawable.type_flying).into(binding.pokemonTypeImage2)
                FIGHTING_TYPE ->
                    Picasso.get().load(R.drawable.type_fighting).into(binding.pokemonTypeImage2)
                PSYCHIC_TYPE ->
                    Picasso.get().load(R.drawable.type_psychic).into(binding.pokemonTypeImage2)
                BUG_TYPE ->
                    Picasso.get().load(R.drawable.type_bug).into(binding.pokemonTypeImage2)
                DRAGON_TYPE ->
                    Picasso.get().load(R.drawable.type_dragon).into(binding.pokemonTypeImage2)
                POISON_TYPE ->
                    Picasso.get().load(R.drawable.type_poison).into(binding.pokemonTypeImage2)
                DARK_TYPE ->
                    Picasso.get().load(R.drawable.type_dark).into(binding.pokemonTypeImage2)
                ELECTRIC_TYPE ->
                    Picasso.get().load(R.drawable.type_electric).into(binding.pokemonTypeImage2)
                STEEL_TYPE ->
                    Picasso.get().load(R.drawable.type_steel).into(binding.pokemonTypeImage2)
            }
        }
    }

    companion object{
        private const val FIRE_TYPE = "fire"
        private const val FAIRY_TYPE = "fairy"
        private const val NORMAL_TYPE = "normal"
        private const val GRASS_TYPE = "grass"
        private const val GHOST_TYPE = "ghost"
        private const val WATER_TYPE = "water"
        private const val ICE_TYPE = "ice"
        private const val ROCK_TYPE = "rock"
        private const val GROUND_TYPE = "ground"
        private const val FLYING_TYPE = "flying"
        private const val FIGHTING_TYPE = "fighting"
        private const val PSYCHIC_TYPE = "psychic"
        private const val BUG_TYPE = "bug"
        private const val DRAGON_TYPE = "dragon"
        private const val POISON_TYPE = "poison"
        private const val DARK_TYPE = "dark"
        private const val ELECTRIC_TYPE = "electric"
        private const val STEEL_TYPE = "steel"
    }
}
