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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.traningcomposeapp.common.TextFieldCompose
import com.example.traningcomposeapp.ui.theme.TextStyleBold24
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.fontFamily
import com.example.traningcomposeapp.utils.Constants.EMPTY
import com.example.traningcomposeapp.utils.RegexUtils

@Composable
fun UserNameScreen(onBackPressed: () -> Unit, onDoneClick: (String) -> Unit) {

    val context = LocalContext.current
    var usernameValue by remember { mutableStateOf(EMPTY) }

    Scaffold(
        topBar = {
            AppToolbar {
                onBackPressed()
            }
        },
        bottomBar = {
            CenterAlignedButton(
                text = stringResource(id = R.string.done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                onDoneClick(usernameValue)
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
                text = stringResource(id = R.string.enter_username),
                color = colorResource(id = R.color.widget_background_1),
                style = TextStyleBold24
            )

            TextFieldCompose(
                textFieldValue = usernameValue,
                onValueChange = {
                    if (it.length <= 20 && it.matches(RegexUtils.onlyAlphabetRegex)) {
                        usernameValue = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyleNormal14,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_person_svg),
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = context.getString(R.string.username),
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
        }
    }
}