package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleNormal10
import com.example.traningcomposeapp.utils.UserDataManager

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(top = 30.dp)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        UserProfileWidget()
        SettingsWidget()
    }
}

@Composable
fun UserProfileWidget() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person_svg),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = UserDataManager.username ?: "Kshitij Mittal",
                style = TextStyleBold16,
                color = Color.White
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_call_outlined),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = UserDataManager.phoneNumber ?: "8178787298",
                    style = TextStyleNormal10,
                    color = Color.White
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.outline_email_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = UserDataManager.email ?: "abcd@gmail.com",
                    style = TextStyleNormal10,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SettingsWidget() {
    val settingsList = listOf("My Ticket")
    repeat(settingsList.size) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_outline_ticket),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
            )
            Text(
                text = settingsList[it],
                style = TextStyleBold14,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null

            )
        }
    }
}
