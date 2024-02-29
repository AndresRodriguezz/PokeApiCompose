package com.example.pokemon.pokemon.data.network

import com.example.pokemon.pokemon.data.model.PokemonEvolutionChainResponse
import com.example.pokemon.pokemon.data.model.PokemonAbilities
import com.example.pokemon.pokemon.data.model.PokemonResponse
import com.example.pokemon.pokemon.data.model.PokemonSpecie
import javax.inject.Inject

class PokemonClient @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun pokemonList(): PokemonResponse = pokemonService.pokemonList()

    suspend fun pokemonSpecie(
        name: String
    ): PokemonSpecie = pokemonService.pokemonDetails(
        name = name
    )

    suspend fun pokemonAbilities(
        pokemonId: String
    ): PokemonAbilities = pokemonService.pokemonAbilities(pokemonId)

    suspend fun pokemonEvolution(
        pokemonEvolutionId: String
    ): PokemonEvolutionChainResponse = pokemonService.pokemonEvolution(pokemonEvolutionId)
}