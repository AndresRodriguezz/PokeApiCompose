package com.example.pokemon.pokemon.data.repository

import com.example.pokemon.pokemon.data.database.dao.PokemonDao
import com.example.pokemon.pokemon.data.database.entities.PokemonEntity
import com.example.pokemon.pokemon.data.model.PokemonAbilities
import com.example.pokemon.pokemon.data.model.PokemonEvolutionChainResponse
import com.example.pokemon.pokemon.data.model.PokemonResponse
import com.example.pokemon.pokemon.data.model.PokemonSpecie
import com.example.pokemon.pokemon.data.network.PokemonClient
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonClient: PokemonClient,
    private val pokemonDao: PokemonDao,
): PokemonRepository {

    override suspend fun getPokemons(): PokemonResponse {
        return pokemonClient.pokemonList()
    }

    override suspend fun getPokemonsFromDB(): List<PokemonEntity> = withContext(Dispatchers.IO) {
      pokemonDao.getAllPokemons()
    }

    override suspend fun updatePokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.updatePokemon(pokemonEntity)
    }

    override suspend fun insertPokemons(pokemons: List<PokemonEntity>) {
        withContext(Dispatchers.IO) {
            pokemonDao.insertPokemons(pokemons)
        }
    }

    override suspend fun getSpecies(name: String): PokemonSpecie {
        return pokemonClient.pokemonSpecie(name)
    }

    override suspend fun getPokemonAbilities(pokemonId: String): PokemonAbilities {
        return pokemonClient.pokemonAbilities(pokemonId)
    }

    override suspend fun getPokemonEvolution(pokemonEvolutionId: String): PokemonEvolutionChainResponse {
        return pokemonClient.pokemonEvolution(pokemonEvolutionId)
    }
}
