package com.example.pokemon.pokemon.domain.use_case

import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.data.database.entities.toDomain
import com.example.pokemon.pokemon.data.database.entities.toPokemonEntity
import com.example.pokemon.pokemon.domain.model.Pokemon
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        try {
            val pokemonsFromDB = repository.getPokemonsFromDB().map { it.toDomain() }
            if (pokemonsFromDB.isEmpty()) {
                fetchAndStorePokemons()
            }
            emit(Resource.Success(pokemonsFromDB))
            val pokemons = repository.getPokemonsFromDB().map { it.toDomain() }
            emit(Resource.Success(pokemons))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
    private suspend fun FlowCollector<Resource<List<Pokemon>>>.fetchAndStorePokemons() {
        val pokemons = repository.getPokemons().results.map { it.toPokemonEntity() }
        repository.insertPokemons(pokemons)
        val pokemonsFromDB = repository.getPokemonsFromDB().map { it.toDomain() }
        emit(Resource.Success(pokemonsFromDB))
    }
}