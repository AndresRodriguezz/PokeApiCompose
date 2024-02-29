package com.example.pokemon.pokemon.data.di

import com.example.pokemon.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.pokemon.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
    fun provideRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}