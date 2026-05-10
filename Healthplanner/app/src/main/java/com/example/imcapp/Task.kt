package com.example.imcapp

data class Task(
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)