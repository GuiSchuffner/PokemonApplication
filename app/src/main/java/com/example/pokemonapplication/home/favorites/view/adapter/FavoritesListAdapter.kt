package com.example.pokemonapplication.home.favorites.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.home.favorites.view.util.PokemonBindHelper
import com.example.pokemonapplication.home.model.Pokemon
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class FavoritesListAdapter(
    private var pokemonList: MutableList<Pokemon>
    ) : RecyclerView.Adapter<FavoritesListAdapter.FavoritesItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return FavoritesItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun resetDataBase(){
        pokemonList= mutableListOf()
        notifyDataSetChanged()
    }

    fun addPokemon(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        notifyItemChanged(itemCount-1)
    }

    override fun onBindViewHolder(holder: FavoritesItemViewHolder, position: Int) {
        holder.bindListItem(pokemonList[position])
    }

    class FavoritesItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        private val pokemonName=
            itemView.findViewById<MaterialTextView>(R.id.list_item_pokemon_name)
        private val pokemonImage: ImageView =
            itemView.findViewById(R.id.list_item_pokemon_image)
        private val pokemonHeight: MaterialTextView =
            itemView.findViewById(R.id.list_item_pokemon_height_value_text)
        private val pokemonWeight: MaterialTextView =
            itemView.findViewById(R.id.list_item_pokemon_weight_value_text)
        private val pokemonType1: ImageView =
            itemView.findViewById(R.id.list_item_pokemon_type_image1)
        private val pokemonType2: ImageView =
            itemView.findViewById(R.id.list_item_pokemon_type_image2)

        fun bindListItem(pokemon: Pokemon) {
            pokemonName.text=pokemon.name
            Picasso.get().load(pokemon.sprites.front_default).into(pokemonImage)
            pokemonHeight.text=pokemon.height.toString()
            pokemonWeight.text=pokemon.weight.toString()
            PokemonBindHelper.bindPokemonTypeImageView(
                pokemon.types[0].type.name, pokemonType1
            )
            if(pokemon.types.size==2){
                pokemonType2.isVisible=true
                PokemonBindHelper.bindPokemonTypeImageView(
                    pokemon.types[1].type.name, pokemonType2
                )
            }
            else{
                pokemonType2.isVisible=false
            }
        }
    }
}
