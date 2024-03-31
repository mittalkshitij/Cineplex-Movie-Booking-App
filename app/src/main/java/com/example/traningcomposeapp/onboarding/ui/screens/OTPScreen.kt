package com.example.traningcomposeapp.onboarding.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.AppToolbar
import com.example.traningcomposeapp.common.CenterAlignedButton
import com.example.traningcomposeapp.common.CountdownTimer
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.ui.theme.TextStyleBold24
import com.example.traningcomposeapp.ui.theme.TextStyleNormal12
import com.example.traningcomposeapp.utils.RegexUtils

@Composable
fun OTPScreen(onBackPressed: () -> Unit, onContinueClick : () -> Unit) {
    Scaffold(
        topBar = {
            AppToolbar {
                onBackPressed()
            }
        },
        bottomBar = {
            CenterAlignedButton(
                text = stringResource(id = R.string.text_continue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                onContinueClick()
            }
        }) { paddingValues ->
        paddingValues.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(top = 50.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_otp_code),
                color = colorResource(id = R.color.widget_background_1),
                style = TextStyleBold24
            )
            Text(
                text = stringResource(id = R.string.enter_otp_mobile_number),
                color = colorResource(id = R.color.widget_background_4),
                style = TextStyleNormal12,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            OTPTextField()
            CountdownTimer(60, TextStyleBold16, Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun OTPTextField() {
    var otpCode by remember {
        mutableStateOf("")
    }
    val onlyNumberRegex = remember { RegexUtils.onlyNumberRegex }
    BasicTextField(
        value = otpCode,
        onValueChange = { newValue ->
            if (newValue.length <= 6 && newValue.matches(onlyNumberRegex))
                otpCode = newValue
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        singleLine = true,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                repeat(6) { index ->
                    val char = when {
                        index >= otpCode.length -> ""
                        else -> otpCode[index].toString()
                    }

                    val isFocused = (otpCode.length == index)

                    Column(
                        modifier = Modifier
                            .width(40.dp)
                            .height(50.dp)
                            .background(
                                colorResource(id = R.color.widget_background_5),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                1.dp,
                                if (isFocused)
                                    colorResource(id = R.color.white)
                                else colorResource(
                                    R.color.widget_background_1
                                ),
                                RoundedCornerShape(8.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = char,
                            color = colorResource(id = R.color.widget_background_4),
                            style = TextStyleBold24
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
