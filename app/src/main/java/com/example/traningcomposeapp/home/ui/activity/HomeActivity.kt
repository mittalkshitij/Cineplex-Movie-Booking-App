package com.example.traningcomposeapp.home.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.traningcomposeapp.home.ui.viewmodel.HomeViewModel
import com.example.traningcomposeapp.navigation.AppNavGraph
import com.example.traningcomposeapp.navigation.BottomNavItem
import com.example.traningcomposeapp.ui.theme.CinePlexAppTheme
import com.example.traningcomposeapp.ui.theme.TextStyleBold10
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CinePlexAppTheme {
                val navController = rememberNavController()
                val bottomNavItem = listOf(
                    BottomNavItem.Home_BottomNav,
                    BottomNavItem.Ticket_BottomNav,
                    BottomNavItem.Movie_BottomNav,
                    BottomNavItem.Profile_BottomNav
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(navController, bottomNavItem)
                        }
                    ) { innerPadding ->
                        AppNavGraph(
                            navController = navController,
                            innerPadding = innerPadding,
                            homeViewModel = homeViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavHostController,
    bottomNavItem: List<BottomNavItem>
) {
    var selectedIconIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavItem.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,
                    selectedTextColor = Color.Yellow,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor =Color.White
                ),
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = {
                    Text(
                        text = item.label,
                        style = TextStyleBold10
                    )
                },
                onClick = {
                    selectedIconIndex = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                }
            )
        }
    }
}