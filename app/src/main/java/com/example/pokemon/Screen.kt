package com.example.pokemon

sealed class Screen(val route: String) {
    data object PokemonListScreen: Screen("pokemon_list_screen")
    data object PokemonDetailScreen: Screen("pokemon_detail_screen")
    data object PokemonAbilitiesScreen: Screen("pokemon_abilities_screen")
}
