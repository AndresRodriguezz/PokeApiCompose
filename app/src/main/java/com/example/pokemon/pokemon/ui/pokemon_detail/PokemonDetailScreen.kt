package com.example.pokemon.pokemon.ui.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemon.R
import com.example.pokemon.Screen

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonName: String,
    pokemonId: String,
    viewModel: PokemonSpeciesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = Unit) { viewModel.getSpecie(pokemonName) }
    Surface(
        color = Color(0xFFA30303),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.specie != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "#$pokemonId",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(12.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = pokemonName.uppercase(),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 36.dp)
                        )
                    }
                    PokemonText(text = "Felicidad Base: ${state.specie.baseHappiness}")
                    PokemonText(text = "Ratio de captura: ${state.specie.captureRate}")
                    PokemonText(
                        text = "Grupos de huevos: ${
                            state.specie.eggGroupsModel.map { it.name }.toString().replace("[", "")
                                .replace("]", "")
                        }"
                    )
                    Spacer(Modifier.height(10.dp))
                    CategoryButton(onClick = { /*TODO*/ }, title = "Linea evolutiva")
                    Spacer(Modifier.height(10.dp))
                    CategoryButton(onClick = { navController.navigate(Screen.PokemonAbilitiesScreen.route + "/$pokemonId") }, title = "Habilidades")
                  Box (
                      modifier = Modifier.fillMaxSize(),
                      contentAlignment = Alignment.Center
                  ) {
                      AsyncImage(
                          model =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId}.png",
                          contentDescription = null,
                          modifier = Modifier.size(width = 250.dp, height = 250.dp),
                          contentScale = ContentScale.Fit)
                  }

                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun PokemonText(text: String?) {
    Text(
        text = text ?: "",
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    )
}

@Composable
internal fun CategoryButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFFCAA32D), Color(0xFF6F5404))
                )
            )
            .padding(8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        Spacer(Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .scale(1f, .5f)
                    .offset(y = 40.dp)
                    .size(40.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Black.copy(alpha = .3f),
                                Color.Transparent
                            ),
                        )
                    )
            )

            AsyncImage(
                model =  R.drawable.ic_evo_foreground,
                contentDescription = null,
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                contentScale = ContentScale.Fit)
        }
    }
}