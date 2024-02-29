package com.example.pokemon.pokemon.ui.pokemon_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemon.R
import com.example.pokemon.pokemon.domain.model.Pokemon

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onItemClick: (Pokemon) -> Unit,
    onItemLongClik: (Pokemon) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Favorito") }
    val options = listOf("Favorito")
    Surface(
        color = Color(0xE9E7473B),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onItemClick(pokemon) }
                .pointerInput(Unit) {
                    detectTapGestures(onLongPress = {
                        expanded = true
                    }, onTap = { onItemClick(pokemon) })
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .size(height = 40.dp, width = 120.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                PokemonName(pokemon.name)
             if(pokemon.favorite) {
                 AsyncImage(
                     model = R.drawable.ic_favorite,
                     contentDescription = "pokemon favorito",
                     modifier = Modifier.size(width = 25.dp, height = 25.dp),
                     contentScale = ContentScale.Inside
                 )
             }
                PokemonId(text = "#${pokemon.id}")
            }
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp),
                    contentScale = ContentScale.Fit
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        selectedOption = option
                        onItemLongClik(pokemon)
                        expanded = false
                    }, text = { Text(option) })
                }
            }
        }
    }
}

@Composable
private fun PokemonName(text: String?) {
    Text(
        text = text ?: "",
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white)
        ),
    )
}

@Composable
private fun PokemonId(text: String?) {
    Text(
        text = text ?: "",
        modifier = Modifier.alpha(0.5f),
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
        )
    )
}
