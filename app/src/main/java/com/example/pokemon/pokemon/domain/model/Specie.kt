package com.example.pokemon.pokemon.domain.model

data class Specie(
    val baseHappiness: Int,
    val captureRate: Int,
    val eggGroupsModel: List<EggGroups>
)

data class EggGroups(
    val name: String
)
