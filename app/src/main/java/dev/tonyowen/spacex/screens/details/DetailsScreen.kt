package dev.tonyowen.spacex.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.components.cards.RocketDetailsListCard
import dev.tonyowen.spacex.ui.components.pager.RocketImagePager
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier, rocketId: String, viewModel: DetailsScreenViewModel = koinViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getRocket(rocketId)
    }

    val rocketResponse by viewModel.rocket.collectAsState()

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = { Text(rocketResponse.data?.name ?: "") })
    }) { innerPadding ->
        when (rocketResponse) {
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
                Text(text = "Error", modifier = Modifier.align(Alignment.Center))
            }

            is NetworkResponse.Success -> {
                val rocket = (rocketResponse as NetworkResponse.Success).data!!

                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    item {
                        RocketImagePager(modifier = Modifier.fillMaxWidth(), rocket = rocket)
                    }

                    item {
                        Card(modifier = Modifier.fillMaxSize()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Designed and manufactured by: ${rocket.company}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Country: ${rocket.country}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "First flight: ${rocket.firstFlight}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Cost per launch: ${NumberFormat.getCurrencyInstance(Locale.US).format(rocket.costPerLaunch)}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Success rate: ${rocket.successRate}%", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }

                    item {
                        Card(modifier = Modifier.fillMaxSize()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = rocket.description, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }

                    item {
                        RocketDetailsListCard(modifier = Modifier.fillMaxWidth(),title = "Specifications", details = listOf(
                            "Height" to rocket.height,
                            "Diameter" to rocket.diameter,
                            "Mass" to rocket.mass
                        ))
                    }

                    item {
                        RocketDetailsListCard(modifier = Modifier.fillMaxWidth(),title = "Engines", details = listOf(
                            "Type" to rocket.engineType,
                            "Number" to rocket.numberOfEngines.toString(),
                            "Thrust" to rocket.thrust,
                            "Fuel" to rocket.fuel
                        ))
                    }

                    item {
                        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                            Text("Want to know more? Head to Wikipedia")
                        }
                    }
                }
            }
        }
    }
}