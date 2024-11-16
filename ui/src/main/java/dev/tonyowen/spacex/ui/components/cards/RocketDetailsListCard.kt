package dev.tonyowen.spacex.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RocketDetailsListCard(modifier: Modifier = Modifier, title: String, details: List<Pair<String, String>>) {
    DarkCard(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            details.forEach {
                Text(text = "${it.first}: ${it.second}", style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))
            }
        }
    }
}