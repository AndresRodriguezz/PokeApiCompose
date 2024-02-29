package com.example.pokemon.pokemon.ui.pokemon_abilities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.domain.use_case.GetPokemonAbilitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonAbilitiesViewModel @Inject constructor(
    private val getPokemonAbilitiesUseCase: GetPokemonAbilitiesUseCase
): ViewModel() {
    private val _state = mutableStateOf(AbilityState())
    val state: State<AbilityState> = _state

    fun getAbilities(pokemonId: String) {
        getPokemonAbilitiesUseCase(pokemonId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = AbilityState(abilities = result.data)
                }
                is Resource.Error -> {
                    _state.value = AbilityState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = AbilityState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}