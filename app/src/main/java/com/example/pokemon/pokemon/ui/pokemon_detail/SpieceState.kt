package com.example.pokemon.pokemon.ui.pokemon_detail

import com.example.pokemon.pokemon.domain.model.Specie

data class SpieceState (
    val isLoading: Boolean = false,
    val specie: Specie? = null,
    val error: String = ""
)