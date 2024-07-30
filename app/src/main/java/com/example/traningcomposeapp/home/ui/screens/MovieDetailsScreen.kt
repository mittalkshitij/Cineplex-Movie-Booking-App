package com.example.traningcomposeapp.home.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.CenterAlignedButton
import com.example.traningcomposeapp.common.compose.HeaderText
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.common.compose.ProfileGlideImage
import com.example.traningcomposeapp.home.data.model.CinemaDetails
import com.example.traningcomposeapp.home.domain.model.CreditsResponse
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.ui.theme.TextStyleBold12
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal10
import com.example.traningcomposeapp.ui.theme.TextStyleNormal12
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.utils.Constants.EMPTY
import com.example.traningcomposeapp.utils.Result

@Composable
fun MovieDetailsScreen(homeViewModel: HomeViewModel, onBackPressed: () -> Unit, onContinuePressed : () -> Unit) {

    var movieDetails by remember { mutableStateOf<MovieResults?>(null) }
    var creditsResponse by remember { mutableStateOf<CreditsResponse?>(null) }

    homeViewModel.movieResults.collectAsStateWithLifecycle().value?.let {
        movieDetails = it
    }

    LaunchedEffect(Unit) {
        movieDetails?.id?.let { homeViewModel.callCreditsApi(it) }
    }

    homeViewModel.creditsResponse.collectAsStateWithLifecycle().value.let {
        when (it) {
            is Result.Success -> {
                creditsResponse = it.data
            }

            is Result.Error -> {}
            Result.Loading -> {}
        }
    }

    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-50).dp)
        ) {
            PosterGlideImage(
                model = movieDetails?.backdropPath ?: EMPTY,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(180.dp)
            )
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.widget_background_7))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = movieDetails?.title ?: EMPTY,
                        style = TextStyleBold18,
                        color = colorResource(id = R.color.widget_background_4)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Duration",
                            style = TextStyleNormal14,
                            color = colorResource(id = R.color.widget_background_8)
                        )
                        Text(
                            text = "  •  ",
                            color = colorResource(id = R.color.widget_background_8)
                        )
                        Text(
                            text = movieDetails?.releaseDate ?: EMPTY,
                            style = TextStyleNormal14,
                            color = colorResource(id = R.color.widget_background_8)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    ReviewSection(movieDetails)
                }
            }
        }
        Column(
            Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row {
                Text(
                    text = "Movie Genre:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "Action, adventure, sci-fi", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
            Row {
                Text(
                    text = "Censorship:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "13+", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
            Row {
                Text(
                    text = "Language:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "English", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
        }
        StorylineSection(movieDetails)
        DirectorSection(creditsResponse)
        CastSection(creditsResponse)
        CinemaSection()
        CenterAlignedButton(
            text = "Continue",
            Modifier.fillMaxWidth(),
            textStyle = TextStyleBold16
        ) {
            onContinuePressed()
        }
    }
}

@Composable
fun ReviewSection(movieDetails: MovieResults?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Review",
            style = TextStyleNormal14,
            color = colorResource(id = R.color.widget_background_4)
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = colorResource(id = R.color.widget_background_1)
        )
        Text(
            text = String.format("%.1f", movieDetails?.voteAverage?.div(2) ?: 1),
            style = TextStyleNormal14,
            color = colorResource(id = R.color.widget_background_4)
        )
    }
}

@Composable
fun StorylineSection(movieDetails: MovieResults?) {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        HeaderText("Storyline")

        Text(
            text = movieDetails?.overview ?: EMPTY,
            style = TextStyleNormal12,
            color = colorResource(id = R.color.widget_background_4)
        )
    }
}

@Composable
fun DirectorSection(creditsResponse: CreditsResponse?) {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HeaderText("Director")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(creditsResponse?.crew ?: emptyList()) {
                MemberCard(it.commonCastCrew.name, it.commonCastCrew.profilePath)
            }
        }
    }
}

@Composable
fun CastSection(creditsResponse: CreditsResponse?) {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HeaderText("Cast")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(creditsResponse?.cast ?: emptyList()) {
                MemberCard(it.commonCastCrew.name, it.commonCastCrew.profilePath)
            }
        }
    }
}

@Composable
fun MemberCard(name: String, profilePath: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.widget_background_7)
        )
    ) {
        Row(
            Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileGlideImage(
                model = profilePath,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .padding(end = 8.dp)
            )

            Text(
                text = name,
                style = TextStyleNormal10,
                softWrap = true,
                color = colorResource(id = R.color.widget_background_4)
            )
        }
    }
}

@Composable
fun CinemaSection() {
    val cinemaList = remember {
        listOf(
            CinemaDetails(
                id = 0,
                name = "PVR Mahagun Mall",
                distance = "2.3 km",
                address = "Vaishali, Ghaziabad",
                logo = R.drawable.pvrcinema,
            ),
            CinemaDetails(
                id = 1,
                name = "INOX Shipra Mall",
                distance = "3.4 km",
                address = "Indirapuram, Ghaziabad",
                logo = R.drawable.inoxcinema,
            ),
            CinemaDetails(
                id = 2,
                name = "US Cinemas Eros Mall",
                distance = "3.7 km",
                address = "Indirapuram, Ghaziabad",
                logo = R.drawable.uscinema,
            )
        )
    }
    var selectedCinema by remember { mutableIntStateOf(cinemaList[0].id) }

    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderText("Cinema")
        repeat(cinemaList.size) {
            val cinemaDetail = cinemaList[it]
            Cinema(cinemaDetail, selectedCinema) { cinemaDetails ->
                selectedCinema = cinemaDetails.id
            }
        }
    }
}

@Composable
fun Cinema(
    cinemaDetail: CinemaDetails,
    selectedCinema: Int,
    onCinemaSelect: (CinemaDetails) -> Unit
) {
    val isSelected = selectedCinema == cinemaDetail.id
    val backgroundColor = if (isSelected) {
        R.color.widget_background_5
    } else {
        R.color.widget_background_7
    }
    val border = if (isSelected) {
        BorderStroke(1.dp, color = colorResource(id = R.color.widget_background_1))
    } else {
        BorderStroke(0.dp, Color.Transparent)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(backgroundColor),
                shape = RoundedCornerShape(12.dp)
            )
            .border(border, shape = RoundedCornerShape(12.dp))
            .clickable {
                onCinemaSelect(cinemaDetail)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = cinemaDetail.name,
                style = TextStyleBold12,
                color = Color.White
            )
            Text(
                text = cinemaDetail.distance + " | " + cinemaDetail.address,
                style = TextStyleNormal12,
                color = Color.White
            )
        }
        Image(
            painter = painterResource(cinemaDetail.logo),
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(16.dp)
        )
    }
}