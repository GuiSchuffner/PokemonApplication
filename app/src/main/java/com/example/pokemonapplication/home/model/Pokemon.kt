package com.example.pokemonapplication.home.model

data class Pokemon(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val forms: List<NamedApiResource>,
    val game_indices: List<VersionGameIndex>,
    val held_items: List<PokemonHeldItem>,
    val location_area_encounters: String,
    val moves: List<PokemonMove>,
    val sprites: PokemonSprites,
    val species: NamedApiResource,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>
)

data class VersionGameIndex(
    val game_index: Int,
    val version: NamedApiResource
)

data class NamedApiResource(
    val name: String,
    val url: String
)

data class PokemonAbility(
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)

data class PokemonHeldItem(
    val item: NamedApiResource,
    val versionDetails: List<PokemonHeldItemVersion>
)

data class PokemonHeldItemVersion(
    val version: NamedApiResource,
    val rarity: Int
)

data class PokemonMove(
    val move: NamedApiResource,
    val versionGroupDetails: List<PokemonMoveVersion>
)

data class PokemonMoveVersion(
    val moveLearnMethod: NamedApiResource,
    val versionGroup: NamedApiResource,
    val levelLearnedAt: Int
)

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    val base_Stat: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class PokemonSprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?,
    val back_female: String?,
    val back_shiny_female: String?,
    val frontFemale: String?,
    val front_shiny_female: String?
)

data class Type(
    val id: Int,
    val name: String,
    val damageRelations: TypeRelations,
    val gameIndices: List<GenerationGameIndex>,
    val generation: NamedApiResource,
    val moveDamageClass: NamedApiResource?,
    val names: List<Name>,
    val pokemon: List<TypePokemon>,
    val moves: List<NamedApiResource>
)

data class GenerationGameIndex(
    val gameIndex: Int,
    val generation: NamedApiResource
)

data class TypePokemon(
    val slot: Int,
    val pokemon: NamedApiResource
)

data class Name(
    val name: String,
    val language: NamedApiResource
)

data class TypeRelations(
    val noDamageTo: List<NamedApiResource>,
    val halfDamageTo: List<NamedApiResource>,
    val doubleDamageTo: List<NamedApiResource>,
    val noDamageFrom: List<NamedApiResource>,
    val halfDamageFrom: List<NamedApiResource>,
    val doubleDamageFrom: List<NamedApiResource>
)