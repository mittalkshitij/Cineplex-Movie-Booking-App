package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.AppToolbar
import com.example.traningcomposeapp.common.compose.CenterAlignedButton
import com.example.traningcomposeapp.common.compose.HeaderText
import com.example.traningcomposeapp.home.data.model.CinemaDetails
import com.example.traningcomposeapp.home.data.model.MovieBookingDetails
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.ui.theme.TextStyleBold20
import com.example.traningcomposeapp.ui.theme.TextStyleNormal10
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.TextStyleNormal16
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SeatSelectionScreen(homeViewModel: HomeViewModel, onBuyClicked: (MovieBookingDetails) -> Unit) {

    val selectedDateIndex = remember { mutableIntStateOf(0) }
    val selectedTimeIndex = remember { mutableIntStateOf(0) }
    val selectedSeats = remember { mutableStateListOf<String>() }
    val timeList = listOf(
        "10:00",
        "11:20",
        "12:40",
        "1:40",
        "3:00",
        "5:30",
        "6:05",
        "6:45",
        "8:30",
        "9:45"
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppToolbar(title = "Select Seat") {}
        Image(
            painter = painterResource(id = R.drawable.ic_cinema_view),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        SeatSection(selectedSeats)
        DateTimeSelectionWidget(timeList, selectedDateIndex, selectedTimeIndex)
        Spacer(modifier = Modifier.weight(1f))
        TotalAmountWidget(
            selectedDateIndex,
            timeList[selectedTimeIndex.intValue],
            selectedSeats,
            homeViewModel.selectedCinema,
            onBuyClicked
        )
    }
}

@Composable
fun SeatSection(selectedSeats: SnapshotStateList<String>) {
    val isSold = remember { mutableStateOf(false) }

    for (char in 'A'..'J') {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (j in 1..10) {
                val seatNumber = "$char$j"
                SeatGrid(
                    isSold = isSold.value,
                    isSelected = selectedSeats.contains(seatNumber),
                    seatNumber = seatNumber
                ) { selectedSeatName, isSeatSelected ->
                    if (isSeatSelected) {
                        selectedSeats.remove(selectedSeatName)
                    } else {
                        selectedSeats.add(selectedSeatName)
                    }
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.widget_background_7),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(18.dp)
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {}
            Text(text = "Available", style = TextStyleNormal14, color = Color.White)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.widget_background_1),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(18.dp)
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {}
            Text(text = "Selected", style = TextStyleNormal14, color = Color.White)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.widget_background_6),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(18.dp)
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {}
            Text(text = "Sold", style = TextStyleNormal14, color = Color.White)
        }
    }
}

@Composable
fun SeatGrid(
    isSold: Boolean,
    isSelected: Boolean,
    seatNumber: String,
    onClick: (String, Boolean) -> Unit
) {

    val seatColor: Color = when {
        isSold -> colorResource(id = R.color.widget_background_6)
        isSelected -> colorResource(id = R.color.widget_background_1)
        else -> colorResource(id = R.color.widget_background_7)
    }

    val textColor = when {
        isSelected -> {
            Color.Black
        }

        else -> {
            Color.White
        }
    }

    Column(
        modifier = Modifier
            .clickable {
                onClick(seatNumber, isSelected)
            }
            .background(
                color = seatColor,
                shape = RoundedCornerShape(4.dp)
            )
            .size(25.dp)
            .padding(3.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = seatNumber,
            style = TextStyleNormal10,
            color = textColor
        )
    }
}

@Composable
fun DateTimeSelectionWidget(
    timeList: List<String>,
    selectedDateIndex: MutableIntState,
    selectedTimeIndex: MutableIntState
) {

    val deviceWidth = LocalConfiguration.current.screenWidthDp

    Column(Modifier.fillMaxWidth()) {
        HeaderText(
            text = "Select Date and Time",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(vertical = 20.dp),
            contentPadding = PaddingValues(horizontal = (deviceWidth / 2).dp)
        ) {
            items(10) { index ->
                DatePill(
                    date = index,
                    isSelected = (index == selectedDateIndex.intValue)
                ) {
                    selectedDateIndex.intValue = index
                }
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = (deviceWidth / 2).dp)
        ) {
            itemsIndexed(timeList) { index, time ->
                TimePill(
                    time = time,
                    isSelected = (index == selectedTimeIndex.intValue)
                ) {
                    selectedTimeIndex.intValue = index
                }
            }
        }
    }
}

@Composable
fun DatePill(date: Int, isSelected: Boolean, onDateClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                onDateClick()
            }
            .background(
                color = if (isSelected) {
                    colorResource(id = R.color.widget_background_1)
                } else colorResource(
                    id = R.color.widget_background_7
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .padding(top = 12.dp, start = 6.dp, end = 6.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dec",
            style = TextStyleNormal14,
            color = if (isSelected) {
                colorResource(id = R.color.black)
            } else {
                colorResource(id = R.color.white)
            }
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = if (isSelected) {
                        colorResource(id = R.color.widget_background_10)
                    } else colorResource(
                        id = R.color.widget_background_11
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = (date + 10).toString(),
                style = TextStyleNormal14,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun TimePill(time: String, isSelected: Boolean, onTimeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable {
                onTimeClick()
            }
            .background(
                color = if (isSelected) {
                    colorResource(id = R.color.widget_background_5)
                } else colorResource(
                    id = R.color.widget_background_7
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = if (isSelected) {
                    1.dp
                } else 0.dp,
                color = if (isSelected) {
                    colorResource(id = R.color.widget_background_1)
                } else colorResource(
                    id = R.color.transparent
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 20.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = time,
            style = TextStyleNormal14,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun TotalAmountWidget(
    selectedDateIndex: MutableIntState,
    selectedTime: String,
    selectedSeats: SnapshotStateList<String>,
    selectedCinemaStateFlow: StateFlow<CinemaDetails?>,
    onBuyClicked: (MovieBookingDetails) -> Unit
) {
    val noOfSeats = selectedSeats.size
    val selectedCinemaDetails = selectedCinemaStateFlow.collectAsStateWithLifecycle().value
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        HorizontalDivider(
            modifier = Modifier.padding(top = 20.dp),
            color = colorResource(id = R.color.widget_background_3)
        )
        Row(Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Total",
                    style = TextStyleNormal16,
                    color = colorResource(id = R.color.white)
                )
                Text(
                    text = "${(noOfSeats * 210).toFloat()} INR",
                    style = TextStyleBold20,
                    color = colorResource(id = R.color.widget_background_1)
                )
            }

            CenterAlignedButton(
                text = "Buy Ticket",
                modifier = Modifier.weight(1f),
                enabled = noOfSeats != 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (noOfSeats == 0) {
                        colorResource(id = R.color.widget_background_8)
                    } else {
                        colorResource(id = R.color.widget_background_1)
                    },
                    contentColor = colorResource(id = R.color.black)
                )
            ) {
                onBuyClicked(
                    MovieBookingDetails(
                        totalAmount = (noOfSeats * 210).toFloat(),
                        date = "Dec ${selectedDateIndex.intValue + 10}",
                        time = selectedTime,
                        seatList = selectedSeats.toList(),
                        cinemaDetails = selectedCinemaDetails
                    )
                )
            }
        }
    }
}