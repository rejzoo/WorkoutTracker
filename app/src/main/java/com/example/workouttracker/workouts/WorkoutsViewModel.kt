package com.example.workouttracker.workouts

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class WorkoutsViewModel(navHostController: NavHostController) : ViewModel() {
    val navController = navHostController
    fun addExercise(selectedExercise: String, sets: Int, reps: Int, weight: Int)
    {

    }
}