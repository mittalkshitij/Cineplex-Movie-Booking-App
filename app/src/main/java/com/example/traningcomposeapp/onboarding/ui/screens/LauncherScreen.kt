package com.example.traningcomposeapp.onboarding.ui.screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.CenterAlignedButton
import com.example.traningcomposeapp.common.compose.CenterAlignedOutlinedButton
import com.example.traningcomposeapp.common.compose.GlideImageCompose
import com.example.traningcomposeapp.common.compose.HorizontalPagerWithIndicator
import com.example.traningcomposeapp.common.compose.TermsAndPrivacyText
import com.example.traningcomposeapp.onboarding.data.model.PagerResponse
import com.example.traningcomposeapp.ui.theme.TextStyleBlack26
import com.example.traningcomposeapp.ui.theme.TextStyleBold16
import com.example.traningcomposeapp.utils.readJSONFromAssets
import com.google.gson.Gson
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LauncherScreen(onSignClicked: (String) -> Unit) {

    val context = LocalContext.current
    val jsonString = readJSONFromAssets(context, "onboardingPager.json")
    val pagerJsonResponse = Gson().fromJson(jsonString, PagerResponse::class.java)

    val pagerState = rememberPagerState(
        pageCount = { pagerJsonResponse.pager.size },
    )

    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(3000)
        with(pagerState) {
            val target =
                if (currentPage < pagerJsonResponse.pager.count() - 1) currentPage + 1 else 0

            animateScrollToPage(
                page = target,
                animationSpec = tween(
                    durationMillis = 0,
                    easing = LinearOutSlowInEasing
                )
            )
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = colorResource(id = R.color.white)
                            )
                        ) {
                            append("Cine")
                        }
                        withStyle(
                            SpanStyle(
                                color = colorResource(id = R.color.widget_background_1)
                            )
                        ) {
                            append("Plex")
                        }
                    },
                    textAlign = TextAlign.Center,
                    style = TextStyleBlack26
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CenterAlignedButton(
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    onSignClicked("Sign in")
                }

                CenterAlignedOutlinedButton(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    onSignClicked("Sign up")
                }

                TermsAndPrivacyText()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
        ) {

            HorizontalPagerWithIndicator(pagerState = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card {
                        GlideImageCompose(
                            model = pagerJsonResponse.pager[page].image,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    Text(
                        text = pagerJsonResponse.pager[page].title,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Center,
                        minLines = 2,
                        style = TextStyleBold16
                    )
                }
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration)
                            colorResource(id = R.color.widget_background_1)
                        else colorResource(id = R.color.widget_background_3)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }
    }
}