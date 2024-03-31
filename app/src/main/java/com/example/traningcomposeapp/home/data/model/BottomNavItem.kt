package com.example.traningcomposeapp.home.data.model

import androidx.annotation.DrawableRes
import com.example.traningcomposeapp.R

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String) {
    data object Home : BottomNavItem("home", R.drawable.ic_home_filled, "Home")
    data object Ticket : BottomNavItem("ticket", R.drawable.ic_ticket_filled, "Ticket")
    data object Movie : BottomNavItem("movie", R.drawable.ic_movie_filled, "Movie")
    data object Profile : BottomNavItem("profile", R.drawable.ic_profile_filled, "Profile")
}