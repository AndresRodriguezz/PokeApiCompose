package com.example.pokemon.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionChainResponse(
    @SerializedName("chain") val chain: ChainResponse
)

data class ChainResponse(
    @SerializedName("species") val species: SpeciesResponse,
    @SerializedName("evolves_to") val evolvesTo: List<ChainResponse>? = null
)

data class SpeciesResponse(
    @SerializedName("name") val name: String
)


