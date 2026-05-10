package com.example.imcapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {

                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = {
                        onToggle()
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = task.title
                )
            }

            Button(
                onClick = {
                    onDelete()
                }
            ) {
                Text("X")
            }
        }
    }
}
