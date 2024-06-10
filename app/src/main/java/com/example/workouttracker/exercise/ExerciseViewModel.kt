package com.example.workouttracker.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.workouttracker.data.DatabaseViewModel
import com.example.workouttracker.data.Exercise
import com.example.workouttracker.data.Workout
import com.example.workouttracker.data.WorkoutDatabase

class ExerciseViewModel(private val database: WorkoutDatabase,
                        databaseViewModel: DatabaseViewModel) : ViewModel() {

    val workouts: LiveData<List<Workout>> = databaseViewModel.workouts

    val exercises: LiveData<List<Exercise>> = databaseViewModel.exercises

    fun formatTime(timeInSeconds: Long): String {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}