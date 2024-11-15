package dev.tonyowen.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dev.tonyowen.spacex.navigation.SpaceNavHost
import dev.tonyowen.spacex.ui.theme.SpacexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()

            SpacexTheme {
                SpaceNavHost(navController = navController)
            }
        }
    }
}