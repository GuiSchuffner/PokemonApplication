package com.example.pokemonapplication.home.search.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.home.model.Pokemon
import com.example.pokemonapplication.home.util.PokemonHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class TypePokemonListAdapter(
    private var pokemonList: MutableList<Pokemon>,
    private val selectPokemonListener: SelectPokemonListener
) : RecyclerView.Adapter<TypePokemonListAdapter.TypePokemonItemViewHolder>() {

    interface SelectPokemonListener {
        fun typePokemonListener(pokemonId: Int)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(
        holder: TypePokemonItemViewHolder,
        position: Int
    ) {
        holder.bindListItem(pokemonList[position], selectPokemonListener)
    }

    fun addPokemon(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        notifyItemChanged(itemCount - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypePokemonItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_type_list_item, parent, false)
        return TypePokemonItemViewHolder(view)
    }

    class TypePokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonName =
            itemView.findViewById<MaterialTextView>(R.id.type_pokemon_name)
        private val pokemonImage: ImageView =
            itemView.findViewById(R.id.type_pokemon_image)
        private val pokemonHeight: MaterialTextView =
            itemView.findViewById(R.id.type_pokemon_height_value_text)
        private val pokemonWeight: MaterialTextView =
            itemView.findViewById(R.id.type_pokemon_weight_value_text)
        private val pokemonType1: ImageView =
            itemView.findViewById(R.id.type_pokemon_type_image1)
        private val pokemonType2: ImageView =
            itemView.findViewById(R.id.type_pokemon_type_image2)
        private val pokemonConstraint =
            itemView.findViewById<ConstraintLayout>(R.id.type_pokemon_info_card_constraint)
        private val addButton: MaterialButton =
            itemView.findViewById(R.id.type_add_button)

        fun bindListItem(pokemon: Pokemon, selectPokemonListener: SelectPokemonListener) {
            pokemonName.text = pokemon.name.replaceFirstChar {
                it.uppercase()
            }
            Picasso.get().load(pokemon.sprites.front_default).into(pokemonImage)
            addButton.setOnClickListener {
                selectPokemonListener.typePokemonListener(pokemon.id)
            }
            pokemonHeight.text = pokemon.height.toString()
            pokemonWeight.text = pokemon.weight.toString()
            PokemonHelper.bindPokemonTypeColorCardView(
                pokemon.types[0].type.name,
                pokemonConstraint,
                itemView
            )
            PokemonHelper.bindPokemonTypeImageView(
                pokemon.types[0].type.name,
                pokemonType1
            )
            if (pokemon.types.size == 2) {
                pokemonType2.isVisible = true
                PokemonHelper.bindPokemonTypeImageView(
                    pokemon.types[1].type.name, pokemonType2
                )
            } else {
                pokemonType2.isVisible = false
            }
        }
    }
}