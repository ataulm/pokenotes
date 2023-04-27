package com.ataulm.pokenotes.domain

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val COOL_POKEMON_COUNT = 151

/**
 * https://pokeapi.co/api/v2/
 */
interface PokeApi {

    /**
     * Returns a list of Pokémon in order of ID.
     *
     * @param limit The number of Pokémon to return in this API call.
     */
    @GET("pokemon")
    suspend fun getAll(@Query("limit") limit: Int = COOL_POKEMON_COUNT): PokemonResponse
}
