package com.example.traningcomposeapp.onboarding.model

import com.google.errorprone.annotations.Keep

@Keep
data class PagerResponse(
    val pager: List<Pager>
)
@Keep
data class Pager(
    val image: String,
    val subtitle: String,
    val title: String
)