package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.GlideImageCompose
import com.example.traningcomposeapp.onboarding.data.model.Pager
import com.example.traningcomposeapp.ui.theme.TextStyleBold14

@Composable
fun MoviesScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            FilterChipWidget()
            MoviesGridWidget()
        }
    }
}

@Composable
fun FilterChipWidget() {
    val chipItems = listOf("Now Playing", "Coming Soon")
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(chipItems.size) { index ->
            FilterChip(
                selected = chipItems[selectedItemIndex] == chipItems[index],
                onClick = { selectedItemIndex = index },
                label = {
                    Text(
                        text = chipItems[index],
                        style = TextStyleBold14,
                        textAlign = TextAlign.Center
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = colorResource(id = R.color.widget_background_1),
                    containerColor = colorResource(id = R.color.widget_background_7),
                    labelColor = colorResource(id = R.color.widget_background_8),
                    selectedLabelColor = colorResource(id = R.color.black)
                ),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(0.dp, colorResource(id = R.color.transparent)),
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun MoviesGridWidget() {
    val list = listOf(
        Pager(
            image = "https://i.etsystatic.com/37166133/r/il/60f034/4087791906/il_570xN.4087791906_jcbj.jpg",
            title = "Get the best seat to your next cinematic treat",
            subtitle = "SubTitle 1"
        ),
        Pager(
            image = "https://pbs.twimg.com/media/FUdlA2uaUAAn3PY.jpg:large",
            title = "Never miss a blockbuster with us",
            subtitle = "SubTitle 2"
        ),
        Pager(
            image = "https://media1.popsugar-assets.com/files/thumbor/k0-1TuJZxd86KVB8tss04CZBacc=/1047x1572/filters:format_auto():quality(85):extract_cover()/2014/06/10/799/n/1922283/a7fd727309a42077_the_fault_in_our_stars_poster.jpg",
            title = "Take a seat, the movie’s about to begin",
            subtitle = "SubTitle 3"
        ),
        Pager(
            image = "https://i.etsystatic.com/37166133/r/il/60f034/4087791906/il_570xN.4087791906_jcbj.jpg",
            title = "Get the best seat to your next cinematic treat",
            subtitle = "SubTitle 1"
        ),
        Pager(
            image = "https://pbs.twimg.com/media/FUdlA2uaUAAn3PY.jpg:large",
            title = "Never miss a blockbuster with us",
            subtitle = "SubTitle 2"
        ),
        Pager(
            image = "https://media1.popsugar-assets.com/files/thumbor/k0-1TuJZxd86KVB8tss04CZBacc=/1047x1572/filters:format_auto():quality(85):extract_cover()/2014/06/10/799/n/1922283/a7fd727309a42077_the_fault_in_our_stars_poster.jpg",
            title = "Take a seat, the movie’s about to begin",
            subtitle = "SubTitle 3"
        ),
        Pager(
            image = "https://i.etsystatic.com/37166133/r/il/60f034/4087791906/il_570xN.4087791906_jcbj.jpg",
            title = "Get the best seat to your next cinematic treat",
            subtitle = "SubTitle 1"
        ),
        Pager(
            image = "https://pbs.twimg.com/media/FUdlA2uaUAAn3PY.jpg:large",
            title = "Never miss a blockbuster with us",
            subtitle = "SubTitle 2"
        ),
        Pager(
            image = "https://media1.popsugar-assets.com/files/thumbor/k0-1TuJZxd86KVB8tss04CZBacc=/1047x1572/filters:format_auto():quality(85):extract_cover()/2014/06/10/799/n/1922283/a7fd727309a42077_the_fault_in_our_stars_poster.jpg",
            title = "Take a seat, the movie’s about to begin",
            subtitle = "SubTitle 3"
        )
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list.size) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                GlideImageCompose(
                    model = list[it].image,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(250.dp)
                        .width(170.dp)
                )
                Text(
                    text = "Movie Title",
                    style = TextStyleBold14,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewWidget() {
    MoviesScreen()
}