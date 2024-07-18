package com.example.traningcomposeapp.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.utils.ImageConfig


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageCompose(
    model: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    GlideImage(
        model = model,
        modifier = modifier,
        contentDescription = description,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PosterGlideImage(
    model: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    GlideImage(
        model = ImageConfig.assetBaseUrl + ImageConfig.posterSize.last() + model,
        modifier = modifier,
        contentDescription = description,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileGlideImage(
    model: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    GlideImage(
        model = ImageConfig.assetBaseUrl + ImageConfig.profileSize.last() + model,
        modifier = modifier,
        contentDescription = description,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        failure = placeholder(R.drawable.baseline_person_24),
        loading = placeholder(R.drawable.baseline_person_24)
    )
}