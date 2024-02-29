package com.example.pokemon.pokemon.domain.repository

import com.example.pokemon.pokemon.data.database.entities.PokemonEntity
import com.example.pokemon.pokemon.data.model.PokemonAbilities
import com.example.pokemon.pokemon.data.model.PokemonEvolutionChainResponse
import com.example.pokemon.pokemon.data.model.PokemonResponse
import com.example.pokemon.pokemon.data.model.PokemonSpecie

interface PokemonRepository {

    suspend fun getPokemons(): PokemonResponse

    suspend fun getPokemonsFromDB(): List<PokemonEntity>

    suspend fun updatePokemon(pokemonEntity: PokemonEntity)

    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    suspend fun getSpecies(name: String): PokemonSpecie

    suspend fun getPokemonAbilities(pokemonId: String): PokemonAbilities

    suspend fun getPokemonEvolution(pokemonEvolutionId: String): PokemonEvolutionChainResponse
}