@file:OptIn(ExperimentalMaterial3Api::class)

package dev.tonyowen.spacex.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               viewModel: HomeScreenViewModel = koinViewModel()) {

    val rocketsResponse = viewModel.rockets.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRockets()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("SpaceX Rockets")})
    }) { innerPadding ->
        Text("Home Screen", modifier = Modifier.padding(innerPadding))
    }
}