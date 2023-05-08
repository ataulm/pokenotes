package com.ataulm.pokenotes.domain

import com.ataulm.pokenotes.network.PokeApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getPokemon(): List<Pokemon> {
        return pokeApi.getAll().results.map {
            Pokemon(name = it.name, artworkUrl = derivedArtworkUrl(it.url))
        }
    }

    /**
     * "Guessing" the GitHub asset URL means we can avoid doing another API call (per Pokémon) to
     * fetch the artwork.
     * To get the actual artwork URL, we'll need to do another API call, but we can "guess" it from
     * the url returned.
     */
    private fun derivedArtworkUrl(url: String): String? {
        return PATTERN_POKEMON_ID.find(url)?.groupValues?.lastOrNull()?.let { id ->
            "$DERIVED_ARTWORK_URL/${id}.png"
        }
    }

    companion object {
        /**
         * Looking for the x in "https://pokeapi.co/api/v2/pokemon/x/"
         */
        private val PATTERN_POKEMON_ID = Regex("""/(\d+)/?$""")
        private const val DERIVED_ARTWORK_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork"
    }
}

