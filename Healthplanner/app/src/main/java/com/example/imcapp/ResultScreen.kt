package com.example.imcapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResultScreen(imc: Float, navController: NavController) {

    val status = when {
        imc < 18.5 -> "BAJO PESO"
        imc < 25 -> "NORMAL"
        imc < 30 -> "SOBREPESO"
        else -> "OBESIDAD"
    }

    val description = when {
        imc < 18.5 -> "Tu peso está por debajo de lo recomendado. Considera mejorar tu alimentación."
        imc < 25 -> "Estás en un rango saludable. ¡Sigue así!"
        imc < 30 -> "Tienes sobrepeso. Podrías mejorar con ejercicio y dieta."
        else -> "Nivel de obesidad. Es recomendable tomar acciones para tu salud."
    }

    val color = when {
        imc < 18.5 -> Color.Yellow
        imc < 25 -> Color(0xFF4CAF50)
        imc < 30 -> Color(0xFFFF9800)
        else -> Color.Red
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                "RESULTADOS",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                "IMC = %.1f".format(imc),
                color = Color(0xFF5E4B8B),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                status,
                color = color,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                description,
                color = Color.Gray
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(
                onClick = {
                    navController.navigate("${Routes.recommendation}/$imc")
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E4B8B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("VER RECOMENDACIONES")
            }

            Button(
                onClick = {
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("RECALCULAR", color = Color.Black)
            }
        }
    }
}