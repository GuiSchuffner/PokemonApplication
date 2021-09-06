package com.example.pokemonapplication.home.util

import android.widget.ImageView
import com.example.pokemonapplication.R
import com.squareup.picasso.Picasso

object PokemonBindHelper {
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

    fun bindPokemonTypeImageView(type: String, imageView: ImageView) {
        when (type) {
            FIRE_TYPE ->
                Picasso.get().load(R.drawable.type_fire).into(imageView)
            FAIRY_TYPE ->
                Picasso.get().load(R.drawable.type_fairy).into(imageView)
            NORMAL_TYPE ->
                Picasso.get().load(R.drawable.type_normal).into(imageView)
            GRASS_TYPE ->
                Picasso.get().load(R.drawable.type_grass).into(imageView)
            GHOST_TYPE ->
                Picasso.get().load(R.drawable.type_ghost).into(imageView)
            WATER_TYPE ->
                Picasso.get().load(R.drawable.type_water).into(imageView)
            ICE_TYPE ->
                Picasso.get().load(R.drawable.type_ice).into(imageView)
            ROCK_TYPE ->
                Picasso.get().load(R.drawable.type_rock).into(imageView)
            GROUND_TYPE ->
                Picasso.get().load(R.drawable.type_ground).into(imageView)
            FLYING_TYPE ->
                Picasso.get().load(R.drawable.type_flying).into(imageView)
            FIGHTING_TYPE ->
                Picasso.get().load(R.drawable.type_fighting).into(imageView)
            PSYCHIC_TYPE ->
                Picasso.get().load(R.drawable.type_psychic).into(imageView)
            BUG_TYPE ->
                Picasso.get().load(R.drawable.type_bug).into(imageView)
            DRAGON_TYPE ->
                Picasso.get().load(R.drawable.type_dragon).into(imageView)
            POISON_TYPE ->
                Picasso.get().load(R.drawable.type_poison).into(imageView)
            DARK_TYPE ->
                Picasso.get().load(R.drawable.type_dark).into(imageView)
            ELECTRIC_TYPE ->
                Picasso.get().load(R.drawable.type_electric).into(imageView)
            STEEL_TYPE ->
                Picasso.get().load(R.drawable.type_steel).into(imageView)
        }
    }
}
