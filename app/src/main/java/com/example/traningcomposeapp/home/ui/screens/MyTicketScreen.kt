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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.AppToolbar
import com.example.traningcomposeapp.common.compose.DashedLine
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.TextStyleNormal18
import com.example.traningcomposeapp.utils.pxToDp

@Composable
fun MyTicketScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.black))
    ) {
        AppToolbar(title = "My Ticket") {}
        TicketSection()
    }
}

@Composable
fun TicketSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White, shape = RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Section1()
        Section2()
        Section3()
    }
}

@Composable
fun Section1() {
    Column {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.uscinema),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Movie Title",
                    style = TextStyleBold18,
                    color = colorResource(id = R.color.black)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
                    verticalAlignment = Alignment.CenterVertically,
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
                        text = "14h15",
                        style = TextStyleBold14,
                        color = Color.Black
                    )
                    Text(
                        text = "10.12.2024",
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
                        text = "Seat H7,H8",
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
fun Section2() {
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
                text = "210.00 INR",
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
                    text = "Cinema Name",
                    style = TextStyleBold18,
                    color = Color.Black
                )
                Text(
                    text = "4th floor, Vincom Ocean Park, Da Ton, Gia Lam, Ha Noi",
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

@Preview
@Composable
private fun DefaultPreview() {
    MyTicketScreen()
}