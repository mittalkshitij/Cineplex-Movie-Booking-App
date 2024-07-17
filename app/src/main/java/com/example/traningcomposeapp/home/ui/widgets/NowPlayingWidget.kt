package com.example.traningcomposeapp.home.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.ui.theme.TextStyleBol12
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal12
import com.example.traningcomposeapp.utils.ImageConfig
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingWidget(nowPlayingResponse: ScreeningAndUpcomingResponse, onClick: () -> Unit) {

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { nowPlayingResponse.movieResults.size },
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
            val movie = nowPlayingResponse.movieResults[page]
            Column(
                modifier = Modifier
                    .clickable {
                        onClick()
                    }
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
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card {
                    PosterGlideImage(
                        model = ImageConfig.assetBaseUrl + "original" + movie.posterPath,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(350.dp)
                    )
                }
                Text(
                    text = movie.title,
                    style = TextStyleBold14,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = colorResource(id = R.color.widget_background_1),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = String.format("%.1f", movie.voteAverage / 2),
                        style = TextStyleBol12,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = "(${movie.voteCount})",
                        style = TextStyleNormal12,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}