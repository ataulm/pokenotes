package com.ataulm.pokenotes.domain

import com.squareup.moshi.Json

data class PokemonEntity(
    @Json(name = "id") val id: String
)
