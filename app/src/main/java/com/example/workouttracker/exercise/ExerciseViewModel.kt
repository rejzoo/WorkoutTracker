package com.example.workouttracker.exercise

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.workouttracker.data.DatabaseViewModel
import com.example.workouttracker.data.Exercise
import com.example.workouttracker.data.Workout
import com.example.workouttracker.data.WorkoutDatabase

class ExerciseViewModel(private val database: WorkoutDatabase,
                        databaseViewModel: DatabaseViewModel,
                        private val popBackStack: () -> Unit) : ViewModel() {
    var currentExercise by mutableIntStateOf(0)
    val showOptions: MutableState<Boolean> = mutableStateOf(false)
    val startWorkout: MutableState<Boolean> = mutableStateOf(false)
    val selectedWorkout: MutableState<Int?> = mutableStateOf(null)

    val workouts: LiveData<List<Workout>> = databaseViewModel.workouts

    val exercises: LiveData<List<Exercise>> = databaseViewModel.exercises

    fun formatTime(timeInSeconds: Long): String {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun getListOfExercises(): List<Exercise> {
        return exercises.value?.filter { exercise ->
            exercise.workoutId == selectedWorkout.value
        } ?: emptyList()
    }

    fun getExerciseName():String {
        val exercisesListForWorkout = getListOfExercises()
        return if (exercisesListForWorkout.isNotEmpty()) {
            exercisesListForWorkout[currentExercise].name
        } else {
            "No exercises available"
        }
    }

    fun getNumberOfSets(): String {
        val exercisesListForWorkout = getListOfExercises()
        return if (exercisesListForWorkout.isNotEmpty()) {
            exercisesListForWorkout[currentExercise].goalSets.toString()
        } else {
            "0"
        }
    }

    fun getNumberOfReps(): String {
        val exercisesListForWorkout = getListOfExercises()
        return if (exercisesListForWorkout.isNotEmpty()) {
            exercisesListForWorkout[currentExercise].goalReps.toString()
        } else {
            "0"
        }
    }

    fun nextExercise() {
        currentExercise += 1
        if (checkEnd()) saveWorkout()
    }

    private fun checkEnd(): Boolean {
        return currentExercise > getListOfExercises().size - 1
    }

    private fun saveWorkout() {
        currentExercise = 0
        // save do databazy
        popBackStack()
    }

    fun back() {
        popBackStack()
    }
}