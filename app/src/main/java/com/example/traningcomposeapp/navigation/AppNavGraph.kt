package com.example.traningcomposeapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.traningcomposeapp.home.ui.screens.HomeScreen
import com.example.traningcomposeapp.home.ui.screens.MovieDetailsScreen
import com.example.traningcomposeapp.home.ui.screens.MoviesScreen
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Home_BottomNav.route,
        androidx.compose.ui.Modifier.padding(innerPadding)
    ) {
        addHomeRoute(navController, homeViewModel)
        addTicketRoute(navController)
        addMovieRoute(navController)
        addProfileRoute(navController)
    }
}

private fun NavGraphBuilder.addHomeRoute(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    navigation(
        route = BottomNavItem.Home_BottomNav.route,
        startDestination = HomeScreen.Home.route
    ) {
        composable(route = HomeScreen.Home.route) {
            HomeScreen(homeViewModel) {
                homeViewModel.setMovieDetails(it)
                navController.navigate(HomeScreen.MovieDetails.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(route = HomeScreen.MovieDetails.route) {
            MovieDetailsScreen(homeViewModel) {
                navController.navigate(HomeScreen.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

private fun NavGraphBuilder.addTicketRoute(navController: NavHostController) {
    composable(BottomNavItem.Ticket_BottomNav.route) { }
}

private fun NavGraphBuilder.addMovieRoute(navController: NavHostController) {
    composable(BottomNavItem.Movie_BottomNav.route) {
        MoviesScreen()
    }
}

private fun NavGraphBuilder.addProfileRoute(navController: NavHostController) {
    composable(BottomNavItem.Profile_BottomNav.route) { }
}