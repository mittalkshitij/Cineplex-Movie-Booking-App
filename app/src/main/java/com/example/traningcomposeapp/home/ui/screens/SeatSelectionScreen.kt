package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.AppToolbar
import com.example.traningcomposeapp.common.compose.CenterAlignedButton
import com.example.traningcomposeapp.common.compose.HeaderText
import com.example.traningcomposeapp.ui.theme.TextStyleBold20
import com.example.traningcomposeapp.ui.theme.TextStyleNormal10
import com.example.traningcomposeapp.ui.theme.TextStyleNormal12
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.TextStyleNormal16

@Composable
fun SeatSelectionScreen(onBuyClicked : () -> Unit) {
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
        SeatGrid()
        DateTimeSelectionWidget()
        Spacer(modifier = Modifier.weight(1f))
        TotalAmountWidget(onBuyClicked)
    }
}

@Composable
fun SeatGrid() {
    for (char in 'A'..'J') {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (j in 1..10) {
                Column(
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.widget_background_7),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .size(25.dp)
                        .padding(3.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$char$j",
                        style = TextStyleNormal10,
                        color = colorResource(id = R.color.white)
                    )
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
                    .size(25.dp)
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
                    .size(25.dp)
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
                    .size(25.dp)
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {}
            Text(text = "Sold", style = TextStyleNormal14, color = Color.White)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateTimeSelectionWidget() {
    Column(Modifier.fillMaxWidth()) {
        HeaderText(
            text = "Select Date and Time",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy((16).dp),
            flingBehavior = rememberSnapFlingBehavior(
                lazyListState = rememberLazyListState()
            ),
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            items(10) {
                DatePill()
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy((16).dp),
            flingBehavior = rememberSnapFlingBehavior(
                lazyListState = rememberLazyListState()
            )
        ) {
            items(10) {
                TimePill()
            }
        }
    }
}

@Composable
fun DatePill() {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.widget_background_1),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(top = 6.dp, start = 4.dp, end = 4.dp, bottom = 2.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dec",
            style = TextStyleNormal12,
            color = colorResource(id = R.color.black)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(color = colorResource(id = R.color.widget_background_10))
                .padding(5.dp)
        ) {
            Text(
                text = "10",
                style = TextStyleNormal12,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun TimePill() {
    Row(
        modifier = Modifier
            .background(
                colorResource(id = R.color.widget_background_5),
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                1.dp,
                color = colorResource(id = R.color.widget_background_1),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "11:05",
            style = TextStyleNormal12,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun TotalAmountWidget(onBuyClicked : () -> Unit) {
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
                    text = "210.00 INR",
                    style = TextStyleBold20,
                    color = colorResource(id = R.color.widget_background_1)
                )
            }

            CenterAlignedButton(text = "Buy Ticket", modifier = Modifier.weight(1f)) {
                onBuyClicked()
            }
        }
    }

}
//
//@Preview
//@Composable
//private fun DefaultPreview() {
//    SeatSelectionScreen()
//}