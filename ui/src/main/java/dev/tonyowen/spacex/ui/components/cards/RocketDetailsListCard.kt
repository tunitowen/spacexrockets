package dev.tonyowen.spacex.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RocketDetailsListCard(modifier: Modifier = Modifier, title: String, details: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            details.forEach {
                Text(text = "${it.first}: ${it.second}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}