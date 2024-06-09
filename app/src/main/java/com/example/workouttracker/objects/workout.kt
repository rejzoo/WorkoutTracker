package com.example.workouttracker.objects

data class Workout(
    val listOfExercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Double
)