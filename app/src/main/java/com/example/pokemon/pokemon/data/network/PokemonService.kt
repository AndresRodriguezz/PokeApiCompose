package com.example.pokemon.pokemon.data.network

import com.example.pokemon.pokemon.data.model.PokemonEvolutionChainResponse
import com.example.pokemon.pokemon.data.model.PokemonAbilities
import com.example.pokemon.pokemon.data.model.PokemonResponse
import com.example.pokemon.pokemon.data.model.PokemonSpecie
import com.example.pokemon.pokemon.data.util.Constants.DEFAULT_ITEMS
import com.example.pokemon.pokemon.data.util.Constants.END_POINT_GET_POKEMONS
import com.example.pokemon.pokemon.data.util.Constants.PARAM_LIMIT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(END_POINT_GET_POKEMONS)
    suspend fun pokemonList(
        @Query(PARAM_LIMIT) limit: Int = DEFAULT_ITEMS,
    ): PokemonResponse

    @GET("pokemon-species/{name}")
    suspend fun pokemonDetails(
        @Path("name") name: String
    ):PokemonSpecie

    @GET("pokemon/{pokemonId}")
    suspend fun pokemonAbilities(
        @Path("pokemonId") pokemonId: String
    ): PokemonAbilities

    @GET("evolution-chain/{pokemonId}")
    suspend fun pokemonEvolution(
        @Path("pokemonEvolutionId") pokemonEvolutionId: String
    ): PokemonEvolutionChainResponse
}