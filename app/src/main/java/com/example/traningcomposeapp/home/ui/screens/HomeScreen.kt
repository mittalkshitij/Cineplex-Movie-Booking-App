package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.GlideImageCompose
import com.example.traningcomposeapp.onboarding.data.model.PagerResponse
import com.example.traningcomposeapp.ui.theme.TextStyleBold10
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleBold20
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.TextStyleNormal16
import com.example.traningcomposeapp.utils.Constants.EMPTY
import com.example.traningcomposeapp.utils.UserDataManager
import com.example.traningcomposeapp.utils.readJSONFromAssets
import com.google.gson.Gson
import kotlin.math.absoluteValue

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        WelcomeBackText()
        //SearchBarWidget()
        NowPlayingWidget()
        ComingSoonWidget()
    }
}

@Composable
fun WelcomeBackText() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Hi, ${UserDataManager.username} \uD83D\uDC4F",
            style = TextStyleNormal16,
            color = colorResource(id = R.color.white),
        )
        Text(
            text = "Welcome back",
            style = TextStyleBold20,
            color = colorResource(id = R.color.white),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWidget() {
    var searchText by rememberSaveable { mutableStateOf(EMPTY) }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        query = searchText,
        onQueryChange = { searchText = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = TextStyleNormal14,
                color = colorResource(id = R.color.widget_background_6)
            )
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        },
        colors = SearchBarDefaults.colors(
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = colorResource(id = R.color.white)
            ),
            containerColor = colorResource(id = R.color.widget_background_7)
        )
    ) {
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingWidget() {
    val context = LocalContext.current
    val jsonString = readJSONFromAssets(context, "onboardingPager.json")
    val pagerJsonResponse = Gson().fromJson(jsonString, PagerResponse::class.java)

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { pagerJsonResponse.pager.size },
    )

    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = stringResource(R.string.now_playing),
            style = TextStyleBold18,
            color = colorResource(id = R.color.widget_background_4),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.3f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                GlideImageCompose(
                    model = pagerJsonResponse.pager[page].image,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(350.dp)
                )
            }
        }
    }
}

@Composable
fun ComingSoonWidget() {
    val context = LocalContext.current

    val jsonString = readJSONFromAssets(context, "onboardingPager.json")
    val pagerJsonResponse = Gson().fromJson(jsonString, PagerResponse::class.java)

    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = stringResource(R.string.coming_soon),
            style = TextStyleBold18,
            color = colorResource(id = R.color.widget_background_4),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(3) { index ->
                Column {
                    Card() {
                        GlideImageCompose(
                            model = pagerJsonResponse.pager[index].image,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.height(250.dp).width(170.dp)
                        )
                    }
                    Text(
                        text = "Movie Title",
                        style = TextStyleBold14,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}