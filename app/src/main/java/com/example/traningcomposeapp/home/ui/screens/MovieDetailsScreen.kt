package com.example.traningcomposeapp.home.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.common.compose.GlideImageCompose
import com.example.traningcomposeapp.common.compose.HeaderText
import com.example.traningcomposeapp.common.compose.MemberCard
import com.example.traningcomposeapp.ui.theme.TextStyleBold18
import com.example.traningcomposeapp.ui.theme.TextStyleNormal12
import com.example.traningcomposeapp.ui.theme.TextStyleNormal14

@Composable
fun MovieDetailsScreen(onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-70).dp)
        ) {
            GlideImageCompose(
                model = "https://i.etsystatic.com/37166133/r/il/60f034/4087791906/il_570xN.4087791906_jcbj.jpg",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(180.dp)
            )
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.widget_background_7))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Movie Title",
                        style = TextStyleBold18,
                        color = colorResource(id = R.color.widget_background_4)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Duration",
                            style = TextStyleNormal14,
                            color = colorResource(id = R.color.widget_background_8)
                        )
                        Text(
                            text = "  •  ",
                            color = colorResource(id = R.color.widget_background_8)
                        )
                        Text(
                            text = "Release Date",
                            style = TextStyleNormal14,
                            color = colorResource(id = R.color.widget_background_8)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    ReviewSection()
                }
            }
        }
        Column(
            Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row {
                Text(
                    text = "Movie Genre:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "Action, adventure, sci-fi", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
            Row {
                Text(
                    text = "Censorship:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "13+", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
            Row {
                Text(
                    text = "Language:", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_9)
                )
                Text(
                    text = "English", style = TextStyleNormal12,
                    color = colorResource(id = R.color.widget_background_4)
                )
            }
        }
        StorylineSection()
        DirectorSection()
        CastSection()
    }
}

@Composable
fun ReviewSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Review",
            style = TextStyleNormal14,
            color = colorResource(id = R.color.widget_background_4)
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = colorResource(id = R.color.widget_background_1)
        )
        Text(
            text = "4.8",
            style = TextStyleNormal14,
            color = colorResource(id = R.color.widget_background_4)
        )
    }
}

@Composable
fun StorylineSection() {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        HeaderText("Storyline")

        Text(
            text = "As the Avengers and their allies have continued to protect the world from threats " +
                    "too large for any one hero to handle, a new danger has " +
                    "emerged from the cosmic shadows: Thanos.... See more",
            style = TextStyleNormal12,
            color = colorResource(id = R.color.widget_background_4)
        )
    }
}

@Composable
fun DirectorSection() {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HeaderText("Director")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(5) {
                MemberCard()
            }
        }
    }
}

@Composable
fun CastSection() {
    Column(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HeaderText("Cast")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(5) {
                MemberCard()
            }
        }
    }
}
