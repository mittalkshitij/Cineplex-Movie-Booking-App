package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.utils.Result

@Composable
fun MoviesScreen(homeViewModel: HomeViewModel, onMovieClick: (MovieResults) -> Unit) {

    val chipItems = remember { listOf("Now Playing", "Coming Soon") }
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    var nowPlayingResponse by remember { mutableStateOf(ScreeningAndUpcomingResponse()) }
    var upcomingResponse by remember { mutableStateOf(ScreeningAndUpcomingResponse()) }

    LaunchedEffect(Unit) {
        homeViewModel.callNowPlayingApi()
    }
    LaunchedEffect(Unit) {
        homeViewModel.callUpcomingApi()
    }

    homeViewModel.nowPlayingResponse.collectAsStateWithLifecycle().value.let {
        when (it) {
            is Result.Success -> {
                nowPlayingResponse = it.data
            }

            is Result.Error -> {}
            Result.Loading -> {}
        }
    }

    homeViewModel.upcomingResponse.collectAsStateWithLifecycle().value.let {
        when (it) {
            is Result.Success -> {
                upcomingResponse = it.data
            }

            is Result.Error -> {}
            Result.Loading -> {}
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            FilterChipWidget(chipItems, selectedItemIndex) {
                selectedItemIndex = it
            }
            if (selectedItemIndex == 0) {
                MoviesGridWidget(nowPlayingResponse) {
                    onMovieClick(it)
                }
            } else {
                MoviesGridWidget(upcomingResponse) {
                    onMovieClick(it)
                }
            }
        }
    }
}

@Composable
fun FilterChipWidget(
    chipItems: List<String>,
    selectedItemIndex: Int,
    onChipClick: (Int) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(chipItems.size) { index ->
            FilterChip(
                selected = chipItems[selectedItemIndex] == chipItems[index],
                onClick = { onChipClick(index) },
                label = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = chipItems[index],
                            style = TextStyleBold14,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 15.dp)
                        )
                    }
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
fun MoviesGridWidget(
    movieResponse: ScreeningAndUpcomingResponse,
    onMovieClick: (MovieResults) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(movieResponse.movieResults.size) {
            val movie = movieResponse.movieResults[it]
            Column(
                modifier = Modifier.clickable {
                    onMovieClick(movie)
                }
            ) {
                Card {
                    PosterGlideImage(
                        model = movie.posterPath,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(250.dp)
                            .width(170.dp)
                    )
                }
                Text(
                    text = movie.title,
                    style = TextStyleBold14,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}