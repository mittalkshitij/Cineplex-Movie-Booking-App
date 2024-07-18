package com.example.traningcomposeapp.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.home.ui.widgets.ComingSoonWidget
import com.example.traningcomposeapp.home.ui.widgets.NowPlayingWidget
import com.example.traningcomposeapp.ui.theme.TextStyleBold20
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.TextStyleNormal16
import com.example.traningcomposeapp.utils.Constants.EMPTY
import com.example.traningcomposeapp.utils.Result
import com.example.traningcomposeapp.utils.UserDataManager

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, onClick: (MovieResults) -> Unit) {

    var nowPlayingResponse by remember { mutableStateOf(ScreeningAndUpcomingResponse()) }
    var upcomingResponse by remember { mutableStateOf(ScreeningAndUpcomingResponse()) }

    LaunchedEffect(Unit) {
        homeViewModel.callNowPlayingApi()
    }
    LaunchedEffect(Unit) {
        homeViewModel.callUpcomingApi()
    }

    homeViewModel.nowPlayingResponse.collectAsStateWithLifecycle().value.let {
        when (it) {
            is Result.Success -> {
                nowPlayingResponse = it.data
            }

            is Result.Error -> {}
            Result.Loading -> {}
        }
    }

    homeViewModel.upcomingResponse.collectAsStateWithLifecycle().value.let {
        when (it) {
            is Result.Success -> {
                upcomingResponse = it.data
            }

            is Result.Error -> {}
            Result.Loading -> {}
        }
    }

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
        NowPlayingWidget(nowPlayingResponse, onClick)
        Spacer(modifier = Modifier.height(20.dp))
        ComingSoonWidget(upcomingResponse, onClick)
        Spacer(modifier = Modifier.height(4.dp))
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