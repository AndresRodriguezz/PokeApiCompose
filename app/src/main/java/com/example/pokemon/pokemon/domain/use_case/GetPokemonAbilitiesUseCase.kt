package com.example.pokemon.pokemon.domain.use_case

import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.data.model.toAbility
import com.example.pokemon.pokemon.domain.model.Ability
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonAbilitiesUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(pokemonId: String): Flow<Resource<List<Ability>>> = flow {
        try {
            emit(Resource.Loading())
            val abilities = repository.getPokemonAbilities(pokemonId = pokemonId).abilities.map { it.toAbility() }
            emit(Resource.Success(abilities))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}