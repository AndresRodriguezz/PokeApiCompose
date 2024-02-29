package com.example.pokemon.pokemon.ui.pokemon_abilities

import com.example.pokemon.pokemon.domain.model.Ability

data class AbilityState(
    val isLoading: Boolean = false,
    val abilities: List<Ability>? = emptyList(),
    val error: String = ""
)