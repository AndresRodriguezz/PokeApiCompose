package com.example.pokemon.pokemon.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemon.pokemon.data.model.PokemonModel
import com.example.pokemon.pokemon.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val favorite: Boolean = false,
)

fun PokemonModel.toPokemonEntity() = PokemonEntity(
    id = getId(),
    name = name,
    url = url,
    imageUrl = getImageUrl()
)

fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    name = name,
    url = url,
    imageUrl = imageUrl,
    favorite = favorite
)