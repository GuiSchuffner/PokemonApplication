package com.example.pokemonapplication.home.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.pokemonapplication.R
import com.squareup.picasso.Picasso

object PokemonHelper {
    const val FIRE_TYPE = "fire"
    const val FAIRY_TYPE = "fairy"
    const val NORMAL_TYPE = "normal"
    const val GRASS_TYPE = "grass"
    const val GHOST_TYPE = "ghost"
    const val WATER_TYPE = "water"
    const val ICE_TYPE = "ice"
    const val ROCK_TYPE = "rock"
    const val GROUND_TYPE = "ground"
    const val FLYING_TYPE = "flying"
    const val FIGHTING_TYPE = "fighting"
    const val PSYCHIC_TYPE = "psychic"
    const val BUG_TYPE = "bug"
    const val DRAGON_TYPE = "dragon"
    const val POISON_TYPE = "poison"
    const val DARK_TYPE = "dark"
    const val ELECTRIC_TYPE = "electric"
    const val STEEL_TYPE = "steel"

    @SuppressLint("ResourceAsColor")
    fun bindPokemonTypeColorCardView(
        type: String,
        constraintLayout: ConstraintLayout,
        itemView: View
    ) {
        when (type) {
            PokemonHelper.FIRE_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.fire
                    )
                )
            PokemonHelper.FAIRY_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.fire
                    )
                )
            PokemonHelper.NORMAL_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.normal
                    )
                )
            PokemonHelper.GRASS_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.grass
                    )
                )
            PokemonHelper.GHOST_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.ghost
                    )
                )
            PokemonHelper.WATER_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.water
                    )
                )
            PokemonHelper.ICE_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.ice
                    )
                )
            PokemonHelper.ROCK_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.rock
                    )
                )
            PokemonHelper.GROUND_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.ground
                    )
                )
            PokemonHelper.FLYING_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.flying
                    )
                )
            PokemonHelper.FIGHTING_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.fighting
                    )
                )
            PokemonHelper.PSYCHIC_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.psychic
                    )
                )
            PokemonHelper.BUG_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.bug
                    )
                )
            PokemonHelper.DRAGON_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.dragon
                    )
                )
            PokemonHelper.POISON_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.poison
                    )
                )
            PokemonHelper.DARK_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.dark
                    )
                )
            PokemonHelper.ELECTRIC_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.electric
                    )
                )
            PokemonHelper.STEEL_TYPE ->
                constraintLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.steel
                    )
                )
        }
    }

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
