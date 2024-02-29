package com.example.pokemon.pokemon.data.model

import com.example.pokemon.pokemon.data.util.Constants.PARAM_RESULTS
import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @SerializedName(PARAM_RESULTS)
    val results: List<PokemonModel>
)