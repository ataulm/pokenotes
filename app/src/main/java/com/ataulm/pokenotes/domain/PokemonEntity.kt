package com.ataulm.pokenotes.domain

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "results") val results: List<PokemonEntity>,
)

data class PokemonEntity(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
) {
    /**
     * To get the actual artwork URL, we'll need to do another API call, but we can "guess" it from
     * the url returned.
     */
    fun artworkUrl(): String? {
        return PATTERN_POKEMON_ID.find(url)?.groupValues?.lastOrNull()?.let { id ->
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
        }
    }

    companion object {
        /**
         * Looking for the x in "https://pokeapi.co/api/v2/pokemon/x/"
         */
        private val PATTERN_POKEMON_ID =Regex("""/(\d+)/?$""")
    }
}