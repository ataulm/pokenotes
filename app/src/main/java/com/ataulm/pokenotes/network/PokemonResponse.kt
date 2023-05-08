package com.ataulm.pokenotes.network

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
)
