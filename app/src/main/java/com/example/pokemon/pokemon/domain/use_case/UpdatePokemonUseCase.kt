package com.example.pokemon.pokemon.domain.use_case

import com.example.pokemon.pokemon.domain.model.Pokemon
import com.example.pokemon.pokemon.domain.model.toPokemonEntity
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class UpdatePokemonUseCase @Inject constructor(private val repository: PokemonRepository)
{
    suspend fun invoke(pokemon: Pokemon) {
        repository.updatePokemon(pokemon.toPokemonEntity())
    }
}