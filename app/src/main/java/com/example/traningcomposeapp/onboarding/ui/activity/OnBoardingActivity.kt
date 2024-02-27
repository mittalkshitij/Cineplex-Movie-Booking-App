package com.example.traningcomposeapp.onboarding.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.traningcomposeapp.onboarding.ui.screens.LauncherScreen
import com.example.traningcomposeapp.onboarding.ui.screens.OTPScreen
import com.example.traningcomposeapp.onboarding.ui.screens.SignScreen
import com.example.traningcomposeapp.onboarding.ui.screens.UserNameScreen
import com.example.traningcomposeapp.ui.theme.TraningComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraningComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingNavigationHandler()
                }
            }
        }
    }
}

@Composable
fun OnBoardingNavigationHandler() {

    val navController = rememberNavController()
    val buttonClick = remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "Launcher") {
        composable(route = "Launcher") {
            LauncherScreen {
                buttonClick.value = it
                navController.navigate("Sign")
            }
        }
        composable(route = "Sign") {
            SignScreen(buttonClick.value, onBackPressed = {
                navController.popBackStack()
            }) {
                navController.navigate("Otp")
            }
        }
        composable(route = "Otp") {
            OTPScreen(onBackPressed = {
                navController.popBackStack()
            }) {
                navController.navigate("Username")
            }
        }
        composable(route = "Username") {
            UserNameScreen(onBackPressed = {
                navController.popBackStack()
            }) {
                //Navigate to HomePage
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TraningComposeAppTheme {

    }
}
