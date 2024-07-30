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
import com.example.traningcomposeapp.home.ui.screens.MyTicketScreen
import com.example.traningcomposeapp.home.ui.screens.PaymentScreen
import com.example.traningcomposeapp.home.ui.screens.SeatSelectionScreen
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
        addMovieRoute(navController, homeViewModel)
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
            MovieDetailsScreen(homeViewModel, onBackPressed = {
                navController.navigate(HomeScreen.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                navController.navigate(HomeScreen.SeatSelection.route) {}
            }
        }

        composable(route = HomeScreen.SeatSelection.route) {
            SeatSelectionScreen {
                navController.navigate(HomeScreen.PaymentScreen.route) {}
            }
        }

        composable(route = HomeScreen.PaymentScreen.route) {
            PaymentScreen {
                navController.navigate(HomeScreen.MyTicket.route) {}
            }
        }

        composable(route = HomeScreen.MyTicket.route) {
            MyTicketScreen()
        }
    }
}

private fun NavGraphBuilder.addTicketRoute(navController: NavHostController) {
    composable(BottomNavItem.Ticket_BottomNav.route) { }
}

private fun NavGraphBuilder.addMovieRoute(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    navigation(
        route = BottomNavItem.Movie_BottomNav.route,
        startDestination = MovieScreen.Movie.route
    ) {
        composable(route = MovieScreen.Movie.route) {
            MoviesScreen(homeViewModel) {
                homeViewModel.setMovieDetails(it)
                navController.navigate(MovieScreen.MovieDetails.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(route = MovieScreen.MovieDetails.route) {
            MovieDetailsScreen(homeViewModel, onBackPressed = {
                navController.navigate(MovieScreen.Movie.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                navController.navigate(MovieScreen.SeatSelection.route) {}
            }
        }

        composable(route = MovieScreen.SeatSelection.route) {
            SeatSelectionScreen {
                navController.navigate(MovieScreen.PaymentScreen.route) {}
            }
        }

        composable(route = MovieScreen.PaymentScreen.route) {
            PaymentScreen {
                navController.navigate(MovieScreen.MyTicket.route) {}
            }
        }

        composable(route = MovieScreen.MyTicket.route) {
            MyTicketScreen()
        }
    }
}

private fun NavGraphBuilder.addProfileRoute(navController: NavHostController) {
    composable(BottomNavItem.Profile_BottomNav.route) { }
}