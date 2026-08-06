package com.example.myapplication // 👈 Ensure this matches your package name!

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Home
                    ) {
                        // 1. Home Destination
                        composable<Home>(
                            exitTransition = {
                                fadeOut(animationSpec = tween(300))
                            },
                            popEnterTransition = {
                                fadeIn(animationSpec = tween(300))
                            }
                        ) {
                            HomeScreen(onShowGreeting = { typedName ->
                                navController.navigate(Greeting(userName = typedName))
                            })
                        }

                        // 2. Greeting Destination (Pop-Up Animation)
                        composable<Greeting>(
                            enterTransition = {
                                scaleIn(
                                    initialScale = 0.7f,
                                    animationSpec = tween(400)
                                ) + fadeIn(animationSpec = tween(400))
                            },
                            exitTransition = {
                                scaleOut(
                                    targetScale = 0.7f,
                                    animationSpec = tween(400)
                                ) + fadeOut(animationSpec = tween(400))
                            },
                            popExitTransition = {
                                scaleOut(
                                    targetScale = 0.7f,
                                    animationSpec = tween(400)
                                ) + fadeOut(animationSpec = tween(400))
                            }
                        ) { backStackEntry ->
                            val greeting: Greeting = backStackEntry.toRoute()
                            GreetingScreen(userName = greeting.userName)
                        }
                    }
                }
            }
        }
    }
}