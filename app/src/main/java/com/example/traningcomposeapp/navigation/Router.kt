package com.example.traningcomposeapp.navigation

import androidx.annotation.DrawableRes
import com.example.traningcomposeapp.R

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String) {
    data object Home_BottomNav : BottomNavItem("home_bottom_nav", R.drawable.ic_home_filled, "Home")
    data object Ticket_BottomNav : BottomNavItem("ticket_bottom_nav", R.drawable.ic_ticket_filled, "Ticket")
    data object Movie_BottomNav : BottomNavItem("movie_bottom_nav", R.drawable.ic_movie_filled, "Movie")
    data object Profile_BottomNav : BottomNavItem("profile_bottom_nav", R.drawable.ic_profile_filled, "Profile")
}

sealed class HomeScreen(val route: String) {
    data object Home : HomeScreen("Home")
    data object MovieDetails : HomeScreen("Movie Details")
}

sealed class MovieScreen(val route: String) {
    data object Movie : MovieScreen("Movie")
    data object MovieDetails : MovieScreen("Movie Details")
}