package com.example.pokemon.pokemon.domain.use_case

import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.data.model.toSpecie
import com.example.pokemon.pokemon.domain.model.Specie
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Resource<Specie>> = flow {
        try {
            emit(Resource.Loading())
            val species = repository.getSpecies(name).toSpecie()
            emit(Resource.Success(species))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}