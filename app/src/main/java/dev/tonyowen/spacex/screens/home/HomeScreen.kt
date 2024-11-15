@file:OptIn(ExperimentalMaterial3Api::class)

package dev.tonyowen.spacex.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.tonyowen.spacex.navigation.DetailsDestination
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.components.cards.RocketCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = koinViewModel(),
    navController: NavHostController
) {

    val rocketsResponse by viewModel.rockets.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRockets()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("SpaceX Rockets") })
    }) { innerPadding ->

        when (rocketsResponse) {
            is NetworkResponse.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is NetworkResponse.Failure -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(text = "Something went wrong", modifier = Modifier.align(Alignment.Center))
            }

            is NetworkResponse.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(24.dp)
                ) {
                    items((rocketsResponse as NetworkResponse.Success).data.orEmpty()) {
                        RocketCard(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(DetailsDestination(it.id))
                            }, rocket = it)
                    }
                }
            }
        }
    }
}