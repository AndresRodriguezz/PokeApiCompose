package com.example.pokemon.pokemon.ui.pokemon_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.domain.model.Pokemon
import com.example.pokemon.pokemon.domain.use_case.GetPokemonsUseCase
import com.example.pokemon.pokemon.domain.use_case.UpdatePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val updatePokemonUseCase: UpdatePokemonUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    init {
        getPokemons()
    }

     fun getPokemons() {
        getPokemonsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PokemonListState(pokemons = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = PokemonListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = PokemonListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun pokemonIsFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            updatePokemonUseCase.invoke(pokemon.copy(favorite = !pokemon.favorite))
        }
    }
}
