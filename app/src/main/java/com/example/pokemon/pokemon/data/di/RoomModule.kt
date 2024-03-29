package com.example.pokemon.pokemon.data.di

import android.content.Context
import androidx.room.Room
import com.example.pokemon.pokemon.data.database.PokemonDataBase
import com.example.pokemon.pokemon.data.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PokemonDataBase =
        Room.databaseBuilder(
            context,
            PokemonDataBase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: PokemonDataBase) = db.pokemonDao()
}