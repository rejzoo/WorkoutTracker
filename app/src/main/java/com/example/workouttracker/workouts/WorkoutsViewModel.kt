package com.example.workouttracker.workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.workouttracker.data.DatabaseViewModel
import com.example.workouttracker.data.Exercise
import com.example.workouttracker.data.Workout
import com.example.workouttracker.data.WorkoutDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutsViewModel(private val database: WorkoutDatabase,
                        databaseViewModel: DatabaseViewModel) : ViewModel() {
    val workouts: LiveData<List<Workout>> = databaseViewModel.workouts

    val exercises: LiveData<List<Exercise>> = databaseViewModel.exercises

    suspend fun createNewWorkout()
    {
        val newWorkout = Workout(
            id = getNextWorkoutId()
        )
        database.workoutDao().insertWorkout(newWorkout)
    }

    private suspend fun getNextWorkoutId(): Int {
        return withContext(Dispatchers.IO) {
            val maxId = database.workoutDao().getMaxWorkoutId() ?: 0
            maxId + 1
        }
    }
}