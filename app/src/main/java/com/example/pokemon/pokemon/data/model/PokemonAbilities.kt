package com.example.pokemon.pokemon.data.model

import com.example.pokemon.pokemon.domain.model.Ability
import com.example.pokemon.pokemon.domain.model.NameAbility
import com.google.gson.annotations.SerializedName

data class PokemonAbilities(
    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,
)

data class PokemonAbility(
    @SerializedName("ability")
    val ability: PokemonNameAbility
)

data class PokemonNameAbility(
    @SerializedName("name")
    val name: String
)

fun PokemonNameAbility.toNameAbility() = NameAbility(
    name = name
)

fun PokemonAbility.toAbility() = Ability(
    ability = ability.toNameAbility()
)
