package com.example.pokemon.pokemon.data.model

import com.example.pokemon.pokemon.domain.model.EggGroups
import com.example.pokemon.pokemon.domain.model.Specie
import com.google.gson.annotations.SerializedName

data class PokemonSpecie(
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("egg_groups")
    val eggGroupsModels: List<EggGroupsModel>
)

data class EggGroupsModel(
    @SerializedName("name")
    val name: String
)

fun EggGroupsModel.toEggGroup() = EggGroups (
    name = name
)

fun PokemonSpecie.toSpecie() = Specie(
    baseHappiness = baseHappiness,
    captureRate = captureRate,
    eggGroupsModel = eggGroupsModels.map { it.toEggGroup() },
)
