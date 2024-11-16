package dev.tonyowen.spacex.ui.components.cards

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DarkCard(modifier: Modifier = Modifier, child: @Composable () -> Unit) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(
        containerColor = Color.White.copy(alpha = 0.1f)
    )) {
        child()
    }
}