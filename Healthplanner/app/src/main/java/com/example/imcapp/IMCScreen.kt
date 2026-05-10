package com.example.imcapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imcapp.Routes
val Purple = Color(0xFF5E4B8B)
val LightPurple = Color(0xFFEDE7F6)
val GrayText = Color(0xFF777777)

@Composable
fun IMCScreen(navController: NavController) {

    var isMale by remember { mutableStateOf(true) }
    var height by remember { mutableStateOf(175f) }
    var weight by remember { mutableStateOf(70) }
    var age by remember { mutableStateOf(25) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

            // 🏷️ Título
            Column {
                Text(
                    "Índice de Masa Corporal",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Ingrese y seleccione la siguiente información",
                    color = GrayText
                )
            }

            // 👤 Género
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                // Hombre
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isMale) Purple else LightPurple
                    ),
                    onClick = { isMale = true }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_male),
                            contentDescription = "Hombre",
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Hombre",
                            color = if (isMale) Color.White else Color.Black
                        )
                    }
                }

                // Mujer
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (!isMale) Purple else LightPurple
                    ),
                    onClick = { isMale = false }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_female),
                            contentDescription = "Mujer",
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Mujer",
                            color = if (!isMale) Color.White else Color.Black
                        )
                    }
                }
            }

            // 📏 Altura
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = LightPurple)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text("Altura", color = GrayText)

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "${height.toInt()}",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Purple,
                            fontWeight = FontWeight.Bold
                        )
                        Text("cm")
                    }

                    Slider(
                        value = height,
                        onValueChange = { height = it },
                        valueRange = 100f..220f,
                        colors = SliderDefaults.colors(
                            thumbColor = Purple,
                            activeTrackColor = Purple
                        )
                    )
                }
            }

            // ⚖️ Peso y Edad
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                // Peso
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Peso (kg)", color = GrayText)
                        Text("$weight", style = MaterialTheme.typography.headlineSmall)

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            CircleButton("-") { if (weight > 1) weight-- }
                            CircleButton("+") { weight++ }
                        }
                    }
                }

                // Edad
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = LightPurple)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Edad", color = GrayText)
                        Text("$age", style = MaterialTheme.typography.headlineSmall)

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            CircleButton("-") { if (age > 1) age-- }
                            CircleButton("+") { age++ }
                        }
                    }
                }
            }
        }

        // 🔘 Botón calcular
        Button(
            onClick = {
                val h = height / 100
                val imc = weight / (h * h)

                navController.navigate("${Routes.result}/$imc")
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text("CALCULAR")
        }
    }
}

// 🔘 Botón circular reutilizable
@Composable
fun CircleButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1C4E9)),
        modifier = Modifier.size(40.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text, color = Color.Black)
    }
}