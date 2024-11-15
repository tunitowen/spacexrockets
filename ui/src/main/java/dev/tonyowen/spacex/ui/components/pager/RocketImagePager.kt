package dev.tonyowen.spacex.ui.components.pager

import android.widget.HorizontalScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.tonyowen.spacex.modules.business.rocket.Rocket

@Composable
fun RocketImagePager(modifier: Modifier = Modifier, rocket: Rocket) {

    val pagerState = rememberPagerState() {
        rocket.images.size
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(16f / 9f)) {
        HorizontalPager(pagerState) { index ->
            AsyncImage(
                model = rocket.images[index],
                contentDescription = "${rocket.name} image ${index + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
        }
        Box(modifier = Modifier.padding(8.dp).align(Alignment.BottomEnd)) {
            Box(
                modifier = Modifier
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
            ) {
                Text(
                    "${pagerState.currentPage + 1} of ${pagerState.pageCount}",
                    color = Color.White,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}