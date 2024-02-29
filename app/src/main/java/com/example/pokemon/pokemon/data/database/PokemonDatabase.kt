package com.example.pokemon.pokemon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.pokemon.data.database.dao.PokemonDao
import com.example.pokemon.pokemon.data.database.entities.PokemonEntity
import com.example.pokemon.pokemon.data.util.Constants.DATABASE_VERSION

@Database(
    entities = [PokemonEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}