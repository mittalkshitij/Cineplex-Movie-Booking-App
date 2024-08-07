package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.AppToolbar
import com.example.traningcomposeapp.common.compose.DashedLine
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.home.data.model.MovieBookingDetails
import com.example.traningcomposeapp.home.data.model.TicketCollectionDetails
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.utils.Constants.EMPTY

@Composable
fun MyTicketScreen(homeViewModel: HomeViewModel) {

    var movieDetails by remember { mutableStateOf<MovieResults?>(null) }
    var movieBookingDetails by remember { mutableStateOf<MovieBookingDetails?>(null) }

    homeViewModel.movieBookingDetails.collectAsStateWithLifecycle().value?.let {
        movieBookingDetails = it
    }

    homeViewModel.movieResults.collectAsStateWithLifecycle().value?.let {
        movieDetails = it
    }

    LaunchedEffect(Unit) {
        homeViewModel.setTicketCollectionDetails(
            TicketCollectionDetails(
                movieBookingDetails = movieBookingDetails,
                movieResults = movieDetails
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.black))
    ) {
        AppToolbar(title = "My Ticket") {}
        TicketSection(movieDetails, movieBookingDetails)
    }
}

@Composable
fun TicketSection(movieDetails: MovieResults?, movieBookingDetails: MovieBookingDetails?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Section1(movieDetails, movieBookingDetails)
        Section2(movieBookingDetails)
        Section3()
    }
}

@Composable
fun Section1(movieDetails: MovieResults?, movieBookingDetails: MovieBookingDetails?) {
    Column {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PosterGlideImage(
                model = movieDetails?.posterPath ?: EMPTY,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(180.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = movieDetails?.title ?: EMPTY,
                    style = TextStyleBold18,
                    color = colorResource(id = R.color.black)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_clock),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            Color.Black
                        ),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "2 hours 29 minutes",
                        style = TextStyleNormal14,
                        color = Color.Black
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            Color.Black
                        ),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Action, Adventure, Sci-fi",
                        style = TextStyleNormal14,
                        color = Color.Black
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Black
                    ),
                    modifier = Modifier.size(48.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = movieBookingDetails?.time ?: EMPTY,
                        style = TextStyleBold14,
                        color = Color.Black
                    )
                    Text(
                        text = movieBookingDetails?.date ?: EMPTY,
                        style = TextStyleBold14,
                        color = Color.Black
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_seat_cinema),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Black
                    ),
                    modifier = Modifier.size(48.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Section 4",
                        style = TextStyleBold14,
                        color = Color.Black
                    )
                    Text(
                        text = "Seat ${movieBookingDetails?.seatList}",
                        style = TextStyleBold14,
                        color = Color.Black
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            color = colorResource(id = R.color.black)
        )
    }
}

@Composable
fun Section2(movieBookingDetails: MovieBookingDetails?) {
    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painterResource(id = R.drawable.baseline_currency_rupee_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Color.Black
                ),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = movieBookingDetails?.totalAmount.toString(),
                style = TextStyleBold18,
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),

            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Color.Black
                ),
                modifier = Modifier.size(24.dp)
            )
            Column {
                Text(
                    text = movieBookingDetails?.cinemaDetails?.name.toString(),
                    style = TextStyleBold18,
                    color = Color.Black
                )
                Text(
                    text = movieBookingDetails?.cinemaDetails?.address.toString(),
                    style = TextStyleNormal14,
                    color = Color.Black
                )
            }
        }

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painterResource(id = R.drawable.outline_text_snippet_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Color.Black
                ),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Show this QR code to the ticket counter to receive your ticket",
                style = TextStyleNormal14,
                color = Color.Black
            )
        }
    }

    DashedLine(
        color = Color.Black,
        strokeWidth = 5f,
        dashWidth = 20f,
        dashGap = 10f,
        modifier = Modifier
            .height(2.dp)
            .padding(
                top = 16.dp
            )
    )
}

@Composable
fun Section3() {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_qr_code),
            contentDescription = null
        )

        Text(
            text = "Oder ID: 78889377726",
            style = TextStyleBold16,
            color = Color.Black
        )
    }
}

//@Preview
//@Composable
//private fun DefaultPreview() {
//    MyTicketScreen()
//}