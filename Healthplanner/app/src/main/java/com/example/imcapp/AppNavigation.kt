package com.example.imcapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.imc
    ) {

        composable(Routes.imc) {
            IMCScreen(navController)
        }

        composable("${Routes.result}/{imc}") { backStack ->
            val imc = backStack.arguments?.getString("imc")?.toFloat() ?: 0f
            ResultScreen(imc, navController)
        }

        composable("${Routes.recommendation}/{imc}") { backStack ->
            val imc = backStack.arguments?.getString("imc")?.toFloat() ?: 0f
            RecommendationScreen(imc, navController)
        }

        composable(Routes.todo) {
            TodoScreen()
        }
    }
}