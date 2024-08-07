package com.example.traningcomposeapp.home.data.model

import com.example.traningcomposeapp.home.domain.model.MovieResults

data class TicketCollectionDetails(
    val movieBookingDetails: MovieBookingDetails? = null,
    val movieResults: MovieResults? = null
)
