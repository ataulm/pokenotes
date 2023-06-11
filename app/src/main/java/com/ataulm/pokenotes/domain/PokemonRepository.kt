package com.ataulm.pokenotes.domain

import com.ataulm.pokenotes.network.PokeApi
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    /**
     * TODO: Observability:
     *  - Return a stream of List<Pokémon> so that any changes to the cache will trigger an update
     *
     * TODO: Persistence:
     *  - Check if we have the list of Pokémon cached, if so return it
     *  - Otherwise fetch from the network, store it in the cache, then return it from the cache
     */
    suspend fun getPokemon(): List<Pokemon> {
        return pokeApi.getAll().results.map {
            Pokemon(
                name = it.name,
                artworkUrl = it.url
            )
        }
    }
}