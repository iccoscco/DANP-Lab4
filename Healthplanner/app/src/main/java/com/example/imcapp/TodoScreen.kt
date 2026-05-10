package com.example.imcapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoScreen() {

    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(1, "Beber 2L de agua"),
                Task(2, "Caminar 30 minutos"),
                Task(3, "Dormir 8 horas"),
                Task(4, "Comer frutas"),
                Task(5, "Hacer ejercicio"),
                Task(6, "Evitar comida chatarra"),
                Task(7, "Reducir azúcar"),
                Task(8, "Hacer cardio"),
                Task(9, "Mantener buena postura"),
                Task(10, "Meditar 10 minutos"),
                Task(11, "Controlar calorías"),
                Task(12, "Consumir proteínas"),
                Task(13, "Tomar descansos"),
                Task(14, "Evitar gaseosas"),
                Task(15, "No saltar comidas"),
                Task(16, "Leer sobre nutrición"),
                Task(17, "Hacer estiramientos"),
                Task(18, "Registrar progreso"),
                Task(19, "Evitar comer tarde"),
                Task(20, "Preparar comida saludable")
            )
        )
    }

    var newTask by remember {
        mutableStateOf("")
    }

    // derivedStateOf
    val completedTasks by remember {
        derivedStateOf {
            tasks.count { it.isDone }
        }
    }

    val progress by remember {
        derivedStateOf {
            if (tasks.isNotEmpty()) {
                completedTasks.toFloat() / tasks.size
            } else {
                0f
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Health Planner",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Estadísticas
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Progreso",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$completedTasks de ${tasks.size} tareas completadas"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Agregar tarea
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = newTask,
                onValueChange = {
                    newTask = it
                },
                label = {
                    Text("Nueva tarea")
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newTask.isNotBlank()) {

                        tasks = tasks + Task(
                            id = tasks.size + 1,
                            title = newTask
                        )

                        newTask = ""
                    }
                }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista
        LazyColumn {

            items(
                items = tasks,
                key = { it.id }
            ) { task ->

                TaskItem(
                    task = task,

                    onToggle = {

                        tasks = tasks.map {

                            if (it.id == task.id) {
                                it.copy(isDone = !it.isDone)
                            } else {
                                it
                            }
                        }
                    },

                    onDelete = {

                        tasks = tasks.filter {
                            it.id != task.id
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}