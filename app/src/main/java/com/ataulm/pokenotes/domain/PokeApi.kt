package com.ataulm.pokenotes.domain

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * baseurl = https://pokeapi.co/api/v2/
 */
interface PokeApi {

    @GET("pokemon")
    suspend fun getAll(@Query("limit") limit: Int): PokemonResponse
}
