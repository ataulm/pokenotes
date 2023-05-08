package com.ataulm.pokenotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataulm.pokenotes.domain.Pokemon
import com.ataulm.pokenotes.network.PokeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeNotesViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val pokemon = pokemonRepository.getPokemon()
            Log.d("!!!", "Pokemon (1-5 of ${pokemon.size})")
            pokemon.take(5).forEach {
                Log.d("!!!", it.name)
            }
        }
    }
}

class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getPokemon(): List<Pokemon> {
        return pokeApi.getAll().results.map {
            Pokemon(
                name = it.name,
                artworkUrl = it.url
            )
        }
    }
}
