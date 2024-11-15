package dev.tonyowen.spacex.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.components.pager.RocketImagePager
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier, rocketId: String, viewModel: DetailsScreenViewModel = koinViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getRocket(rocketId)
    }

    val rocketResponse by viewModel.rocket.collectAsState()

    val pagerState = rememberPagerState() {
        rocketResponse.data?.images?.size ?: 0
    }

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
                }
            }
        }
    }
}