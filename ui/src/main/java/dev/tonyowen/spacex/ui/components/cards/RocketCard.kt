package dev.tonyowen.spacex.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.tonyowen.spacex.modules.business.rocket.Rocket

@Composable
fun RocketCard(modifier: Modifier = Modifier, rocket: Rocket) {

    Card(modifier = modifier) {
        Box {
            AsyncImage(rocket.images.first(), rocket.name, modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f), contentScale = ContentScale.Crop)
            Box(modifier = Modifier.fillMaxWidth().background(color = Color.Black.copy(alpha = 0.5f)).align(Alignment.BottomStart)) {
                Text(rocket.name, color = Color.White, modifier = Modifier.padding(16.dp))
            }
        }
    }
}