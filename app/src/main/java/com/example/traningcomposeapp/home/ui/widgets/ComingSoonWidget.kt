package com.example.traningcomposeapp.home.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.PosterGlideImage
import com.example.traningcomposeapp.home.domain.model.MovieResults
import com.example.traningcomposeapp.home.domain.model.ScreeningAndUpcomingResponse
import com.example.traningcomposeapp.ui.theme.TextStyleBold14
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14
import com.example.traningcomposeapp.ui.theme.White
import com.example.traningcomposeapp.ui.theme.Yellow

@Composable
fun ComingSoonWidget(
    upcomingResponse: ScreeningAndUpcomingResponse,
    onClick: (MovieResults) -> Unit
) {

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
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(upcomingResponse.movieResults.size) { index ->
                val movie = upcomingResponse.movieResults[index]
                Column(
                    modifier = Modifier
                        .width(170.dp)
                        .clickable {
                            onClick(movie)
                        }
                ) {
                    Card {
                        PosterGlideImage(
                            model = movie.posterPath,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        )
                    }
                    Text(
                        text = movie.title,
                        style = TextStyleBold14,
                        color = Yellow,
                        overflow = TextOverflow.Clip,
                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp, end = 4.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_calendar_svg),
                            contentDescription = null
                        )
                        Text(
                            text = movie.releaseDate,
                            style = TextStyleNormal14,
                            color = White
                        )
                    }
                }
            }
        }
    }
}