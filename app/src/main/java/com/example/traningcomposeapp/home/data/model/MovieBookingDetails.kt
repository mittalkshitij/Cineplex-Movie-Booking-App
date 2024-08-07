package com.example.traningcomposeapp.home.data.model

data class MovieBookingDetails(
    val totalAmount : Float,
    val seatList : List<String>,
    val cinemaDetails: CinemaDetails?,
    val date : String,
    val time : String
)
