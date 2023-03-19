package com.example.api_mvvm_kotlin.ui

import Fact
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api_mvvm_kotlin.ui.theme.API_MVVM_kotlinTheme
import com.example.api_mvvm_kotlin.viewmodel.FactUIState
import com.example.api_mvvm_kotlin.viewmodel.FactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            API_MVVM_kotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CatFactApp()
                }
            }
        }
    }
}

@Composable
fun CatFactApp(factViewModel: FactViewModel = viewModel()) {
    FactScreen(uiState = factViewModel.factUIState)
    Scaffold (
        topBar = { TopAppBar(
            title = { Text("Cat facts")}
        )
        },
        content = { FactScreen(uiState = factViewModel.factUIState) })
    }


@Composable
fun FactScreen(uiState: FactUIState) {
    when (uiState) {
        is FactUIState.Loading -> LoadingScreen()
        is FactUIState.Success -> FactList(uiState.fact)
        is FactUIState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Text("Loading cat info, might take seconds")
}

@Composable
fun ErrorScreen() {
    Text("Error retrieving data from cat API :(")
}

@Composable
fun FactList(facts: List<Fact>) {
    LazyColumn(
        Modifier.padding(8.dp)
    ) {
        items(facts) { fact ->
            Text(
                text = fact.title,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Divider(
                color = Color.LightGray, thickness = 1.dp
            )
        }
    }
}