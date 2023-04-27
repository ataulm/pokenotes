package com.ataulm.pokenotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataulm.pokenotes.domain.PokeApi
import com.ataulm.pokenotes.domain.PokemonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeNotesViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val pokemonResponse = pokemonRepository.getPokemon()
            Log.d("!!!", pokemonResponse.results.toString())
        }
    }
}

class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    /**
     * TODO: we don't want PokemonResponse here which is a network entity. We want to return the
     *  domain entity. Then add a unit test for transformations.
     */
    suspend fun getPokemon(): PokemonResponse {
        return pokeApi.getAll()
    }
}