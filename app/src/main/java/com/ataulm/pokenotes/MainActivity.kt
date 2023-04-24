package com.ataulm.pokenotes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataulm.pokenotes.domain.PokeApi
import com.ataulm.pokenotes.ui.theme.PokénotesTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokénotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Foo()
                }
            }
        }
    }

    @Composable
    private fun Foo(
        viewModel: PokeNotesViewModel = hiltViewModel()
    ) {
        Text("hello")
    }
}

@HiltViewModel
class PokeNotesViewModel @Inject constructor(
    private val pokeApi: PokeApi
) : ViewModel() {

    init {
        viewModelScope.launch {
            val all = pokeApi.getAll(150)
            Log.d("!!!", all.toString())
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object PokeApiModule {

    @Provides
    fun providesPokeApi(): PokeApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }
}
