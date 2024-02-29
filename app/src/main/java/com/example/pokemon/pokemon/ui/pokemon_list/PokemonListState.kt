package com.example.pokemon.pokemon.ui.pokemon_list

import com.example.pokemon.pokemon.domain.model.Pokemon

data class PokemonListState (
    val isLoading: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val error: String = ""
)
