package com.example.imcapp

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecommendationScreen(imc: Float, navController: NavController) {

    val recommendations by remember(imc) {
        derivedStateOf {
            when {
                imc < 18.5 -> listOf(
                    "Aumentar calorías",
                    "Comer más proteína",
                    "Entrenamiento de fuerza"
                )
                imc < 25 -> listOf(
                    "Mantener dieta balanceada",
                    "Ejercicio regular",
                    "Dormir bien"
                )
                else -> listOf(
                    "Reducir calorías",
                    "Ejercicio cardio",
                    "Evitar comida chatarra"
                )
            }
        }
    }

    Column(modifier = Modifier.padding(20.dp)) {

        Text("Recomendaciones")

        Spacer(modifier = Modifier.height(16.dp))

        recommendations.forEach {
            Text("• $it")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(Routes.todo)
        }) {
            Text("IR A TAREAS")
        }
    }
}