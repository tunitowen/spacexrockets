package dev.tonyowen.spacex.screens.details

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.components.cards.DarkCard
import dev.tonyowen.spacex.ui.components.cards.RocketDetailsListCard
import dev.tonyowen.spacex.ui.components.pager.RocketImagePager
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    rocketId: String,
    viewModel: DetailsScreenViewModel = koinViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.getRocket(rocketId)
    }

    val rocketResponse by viewModel.rocket.collectAsState()
    val context = LocalContext.current

    Scaffold(modifier = modifier, topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, null, tint = Color.White)
                }
            },
            title = { Text(rocketResponse.data?.name ?: "", color = Color.White) }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Black
            )
        )
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

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        RocketImagePager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp), rocket = rocket
                        )
                    }

                    item {
                        DarkCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Designed and manufactured by: ${rocket.company}",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                )
                                Text(text = "Country: ${rocket.country}", style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))
                                Text(
                                    text = "First flight: ${rocket.firstFlight}",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                )
                                Text(
                                    text = "Cost per launch: ${NumberFormat.getCurrencyInstance(Locale.US).format(rocket.costPerLaunch)}",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                )
                                Text(
                                    text = "Success rate: ${rocket.successRate}%",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                                )
                            }
                        }
                    }

                    item {
                        DarkCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = rocket.description, style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))
                            }
                        }
                    }

                    item {
                        RocketDetailsListCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp), title = "Specifications", details = listOf(
                                "Height" to rocket.height,
                                "Diameter" to rocket.diameter,
                                "Mass" to rocket.mass
                            )
                        )
                    }

                    item {
                        RocketDetailsListCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp), title = "Engines", details = listOf(
                                "Type" to rocket.engineType,
                                "Number" to rocket.numberOfEngines.toString(),
                                "Thrust" to rocket.thrust,
                                "Fuel" to rocket.fuel
                            )
                        )
                    }

                    item {
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp), onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rocket.wikipediaLink))
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Oops, something went wrong", Toast.LENGTH_SHORT).show()
                                }

                            }, colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White, containerColor = Color.Black
                            )
                        ) {
                            Text("Want to know more? Head to Wikipedia")
                        }
                        Spacer(modifier = Modifier.padding(32.dp))
                    }
                }
            }
        }
    }
}