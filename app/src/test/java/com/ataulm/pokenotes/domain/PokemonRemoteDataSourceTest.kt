package com.ataulm.pokenotes.domain

import com.ataulm.pokenotes.network.PokeApi
import com.ataulm.pokenotes.network.PokemonEntity
import com.ataulm.pokenotes.network.PokemonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PokemonRemoteDataSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `calculates official artwork URL`() = runTest {
        val bulbasaur =
            PokemonEntity(name = "Bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val dataSource = PokemonRemoteDataSource(pokeApi = SingleEntityStubPokeApi(bulbasaur))

        val pokemon = dataSource.getPokemon()

        val expectedArtworkUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        assertThat(pokemon)
            .isEqualTo(listOf(Pokemon("Bulbasaur", expectedArtworkUrl)))
    }
}

private class SingleEntityStubPokeApi(private val pokemonEntity: PokemonEntity) : PokeApi {
    override suspend fun getAll(limit: Int): PokemonResponse {
        return runBlocking {
            PokemonResponse(
                1,
                null, null,
                listOf(pokemonEntity)
            )
        }
    }
}
