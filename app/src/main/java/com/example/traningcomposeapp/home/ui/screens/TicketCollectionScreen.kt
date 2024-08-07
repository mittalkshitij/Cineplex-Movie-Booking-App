package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.home.data.model.TicketCollectionDetails
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.ui.theme.TextStyleBold12
import com.example.traningcomposeapp.ui.theme.TextStyleNormal10
import com.example.traningcomposeapp.utils.Constants

@Composable
fun TicketCollectionScreen(homeViewModel: HomeViewModel) {

    var ticketCollection by remember {
        mutableStateOf(listOf(TicketCollectionDetails()))
    }

    homeViewModel.ticketCollectionDetails.collectAsStateWithLifecycle().value.let {
        ticketCollection = it
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .padding(16.dp)
    ) {
        AppToolbar(title = "My Ticket", showNavigationIcon = false)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            itemsIndexed(ticketCollection) { _, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(
                            color = colorResource(id = R.color.widget_background_7),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PosterGlideImage(
                        model = item.movieResults?.posterPath ?: Constants.EMPTY,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(130.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    )

                    Column(
                        modifier = Modifier.fillMaxHeight().padding(vertical = 20.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = item.movieResults?.title ?: Constants.EMPTY,
                            style = TextStyleBold12,
                            color = Color.White
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painterResource(id = R.drawable.ic_clock),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    Color.White
                                ),
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "${item.movieBookingDetails?.date} • ${item.movieBookingDetails?.time}",
                                style = TextStyleNormal10,
                                color = Color.White
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    Color.White
                                ),
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = item.movieBookingDetails?.cinemaDetails?.name
                                    ?: Constants.EMPTY,
                                style = TextStyleNormal10,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}