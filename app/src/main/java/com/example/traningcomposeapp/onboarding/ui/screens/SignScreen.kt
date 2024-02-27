package com.example.traningcomposeapp.onboarding.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.AppToolbar
import com.example.traningcomposeapp.common.CenterAlignedButton
import com.example.traningcomposeapp.common.TermsAndPrivacyText
import com.example.traningcomposeapp.common.TextFieldCompose
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.fontFamily
import com.example.traningcomposeapp.utils.RegexUtils

@Composable
fun SignScreen(signText: String, onBackPressed: () -> Unit = {}, onContinueClick: () -> Unit) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppToolbar(title = signText) {
                onBackPressed()
            }
        },
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                TermsAndPrivacyText()
            }
        }
    ) { paddingValues ->
        paddingValues.calculateTopPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TextFieldCompose(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                regex = RegexUtils.onlyNumberRegex,
                maxTextLength = 10,
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_call_svg),
                        contentDescription = null
                    )
                },
                textStyle = TextStyleNormal14,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                placeholder = {
                    Text(
                        text = context.getString(R.string.enter_phone_number),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.widget_background_2)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(id = R.color.white),
                    focusedContainerColor = colorResource(id = R.color.transparent),
                    unfocusedContainerColor = colorResource(id = R.color.transparent),
                    focusedIndicatorColor = colorResource(id = R.color.widget_background_1),
                    disabledIndicatorColor = colorResource(id = R.color.white),
                    cursorColor = colorResource(id = R.color.white)
                )
            )
            CenterAlignedButton(
                text = stringResource(id = R.string.text_continue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                onContinueClick()
            }
        }
    }
}