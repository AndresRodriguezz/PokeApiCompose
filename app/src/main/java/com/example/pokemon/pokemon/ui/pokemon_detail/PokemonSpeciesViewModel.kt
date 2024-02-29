package com.example.pokemon.pokemon.ui.pokemon_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.pokemon.common.Resource
import com.example.pokemon.pokemon.domain.use_case.GetSpeciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonSpeciesViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
): ViewModel() {
    private val _state = mutableStateOf(SpieceState())
    val state: State<SpieceState> = _state

    fun getSpecie(name: String) {
        getSpeciesUseCase(name).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = SpieceState(specie = result.data)
                }
                is Resource.Error -> {
                    _state.value = SpieceState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = SpieceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}