package com.example.pokemon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.pokemon.pokemon.ui.pokemon_abilities.PokemonAbilitiesScreen
import com.example.pokemon.pokemon.ui.pokemon_detail.PokemonDetailScreen
import com.example.pokemon.pokemon.ui.pokemon_list.PokemonListScreen
import com.example.pokemon.ui.theme.PokemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var keepSplash = true
        val delay = 5000L

        installSplashScreen().setKeepOnScreenCondition { keepSplash }
        Handler(Looper.getMainLooper()).postDelayed({
            keepSplash = false
        }, delay)
        actionBar?.hide()
        setContent {
            PokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xE9F15044)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonListScreen.route,
                    ) {
                        composable(
                            route = Screen.PokemonListScreen.route
                        ) {
                            PokemonListScreen(navController)
                        }
                        composable(
                            route = Screen.PokemonDetailScreen.route + "/{pokemonName}" + "/{pokemonId}",
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "https://andres-jj.com/{pokemonId}/{pokemonName}"
                                    action = Intent.ACTION_VIEW
                                }
                            ),
                            arguments = listOf(
                                navArgument("pokemonName") {
                                    type = NavType.StringType
                                },
                                navArgument("pokemonId") {
                                    type = NavType.StringType
                                }
                            )

                        ) {
                            PokemonDetailScreen(
                                navController,
                                pokemonName = it.arguments?.getString("pokemonName") ?: "",
                                pokemonId = it.arguments?.getString("pokemonId") ?: "",
                            )
                        }
                        composable(
                            route = Screen.PokemonAbilitiesScreen.route + "/{pokemonId}",

                            arguments = listOf(navArgument("pokemonId") {
                                type = NavType.StringType
                            })
                        ) {
                            PokemonAbilitiesScreen(
                                pokemonId = it.arguments?.getString("pokemonId") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}



