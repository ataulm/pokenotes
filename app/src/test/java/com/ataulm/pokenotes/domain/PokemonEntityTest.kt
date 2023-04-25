package com.ataulm.pokenotes.domain

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as isEqualTo

class PokemonEntityTest {
    @Test
    fun `calculates official artwork URL`() {
        val bulbasaur =
            PokemonEntity(name = "Bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")

        assertThat(
            bulbasaur.artworkUrl(),
            isEqualTo("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
        )
    }
}
