package dev.tonyowen.spacex.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.tonyowen.spacex.screens.details.DetailsScreen
import dev.tonyowen.spacex.screens.home.HomeScreen

@Composable
fun SpaceNavHost(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(modifier = modifier, navController = navController, startDestination = HomeDestination) {
        composable<HomeDestination>() {
            HomeScreen(navController = navController)
        }
        composable<DetailsDestination> {
            val args = it.toRoute<DetailsDestination>()
            DetailsScreen(rocketId = args.id)
        }
    }

}