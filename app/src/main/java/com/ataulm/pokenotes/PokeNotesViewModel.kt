package com.ataulm.pokenotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataulm.pokenotes.domain.PokemonRepository
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
