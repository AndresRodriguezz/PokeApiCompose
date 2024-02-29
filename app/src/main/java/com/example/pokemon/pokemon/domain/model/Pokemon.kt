package com.example.pokemon.pokemon.domain.model

import com.example.pokemon.pokemon.data.database.entities.PokemonEntity

data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val favorite: Boolean
)

fun Pokemon.toPokemonEntity() = PokemonEntity(
    id = id,
    name = name,
    url = url,
    imageUrl = imageUrl,
    favorite = favorite
)
