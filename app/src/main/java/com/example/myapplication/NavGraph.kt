package com.example.myapplication // 👈 Ensure package matches

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        // 1. Home Destination
        composable<Home>(
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) }
        ) {
            HomeScreen(onShowGreeting = { typedName ->
                navController.navigate(Greeting(userName = typedName))
            })
        }

        // 2. Greeting Destination
        composable<Greeting>(
            enterTransition = {
                scaleIn(initialScale = 0.7f, animationSpec = tween(400)) + fadeIn(animationSpec = tween(400))
            },
            exitTransition = {
                scaleOut(targetScale = 0.7f, animationSpec = tween(400)) + fadeOut(animationSpec = tween(400))
            },
            popExitTransition = {
                scaleOut(targetScale = 0.7f, animationSpec = tween(400)) + fadeOut(animationSpec = tween(400))
            }
        ) { backStackEntry ->
            val greeting: Greeting = backStackEntry.toRoute()

            // ➕ Pass popBackStack() so clicking the button pops the current destination
            GreetingScreen(
                userName = greeting.userName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}