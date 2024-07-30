package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.AppToolbar
import com.example.traningcomposeapp.common.compose.CenterAlignedButton
import com.example.traningcomposeapp.common.compose.HeaderText
import com.example.traningcomposeapp.home.data.model.PaymentMethodDetails
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleBold24
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14

@Composable
fun PaymentScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AppToolbar(title = "Payment") {}
        MovieDetailsSection()
        BookingSection()
        PaymentMethodSection()
        Spacer(modifier = Modifier.weight(1f))
        CenterAlignedButton(
            text = "Continue",
            Modifier.fillMaxWidth(),
            textStyle = TextStyleBold16
        ) {
            onContinueClicked()
        }
    }
}

@Composable
fun MovieDetailsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(
                color = colorResource(id = R.color.widget_background_7),
                shape = RoundedCornerShape(12.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.inoxcinema),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Movie Title",
                style = TextStyleBold18,
                color = colorResource(id = R.color.widget_background_1)
            )
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
                    modifier = Modifier
                        .size(14.dp)
                )
                Text(text = "Cinema Name", style = TextStyleNormal14, color = Color.White)
            }
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
                Text(text = "10.12.2022 • 14:15", style = TextStyleNormal14, color = Color.White)
            }
        }
    }
}

@Composable
fun BookingSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Order ID",
                style = TextStyleNormal14,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = "7847198199",
                style = TextStyleBold14,
                color = colorResource(id = R.color.white)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Seat",
                style = TextStyleNormal14,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = "H7, H8",
                style = TextStyleBold14,
                color = colorResource(id = R.color.white)
            )
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                style = TextStyleNormal14,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = "540.000 INR",
                style = TextStyleBold24,
                color = colorResource(id = R.color.widget_background_1)
            )
        }
    }
}

@Composable
fun PaymentMethodSection() {
    HeaderText(text = "Payment Method")
    val paymentMethodList = remember {
        listOf(
            PaymentMethodDetails(
                id = 0,
                name = "UPI",
                logo = R.drawable.pvrcinema,
            ),
            PaymentMethodDetails(
                id = 1,
                name = "GPAY",
                logo = R.drawable.inoxcinema,
            ),
            PaymentMethodDetails(
                id = 2,
                name = "PAYTM",
                logo = R.drawable.uscinema,
            )
        )
    }
    var selectedPaymentMethod by remember { mutableIntStateOf(paymentMethodList[0].id) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(paymentMethodList.size) {
            val paymentMethodDetails = paymentMethodList[it]
            PaymentMethod(paymentMethodDetails, selectedPaymentMethod) { paymentDetails ->
                selectedPaymentMethod = paymentDetails.id
            }
        }
    }
}

@Composable
fun PaymentMethod(
    paymentMethodDetails: PaymentMethodDetails,
    selectedPaymentMethod: Int,
    onPaymentMethodSelected: (PaymentMethodDetails) -> Unit
) {
    val isSelected = selectedPaymentMethod == paymentMethodDetails.id
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
                onPaymentMethodSelected(paymentMethodDetails)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(paymentMethodDetails.logo),
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .height(40.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = paymentMethodDetails.name,
                style = TextStyleBold16,
                color = Color.White
            )
        }
        Image(
            imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                Color.White
            )
        )
    }
}

//@Preview
//@Composable
//private fun DefaultPreview() {
//    PaymentScreen {
//        oute) {}
//    }
//}