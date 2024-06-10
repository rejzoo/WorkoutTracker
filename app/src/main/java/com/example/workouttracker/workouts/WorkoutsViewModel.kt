package com.example.workouttracker.workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouttracker.data.Exercise
import com.example.workouttracker.data.Workout
import com.example.workouttracker.data.WorkoutDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutsViewModel(private val database: WorkoutDatabase) : ViewModel() {


    val workouts: LiveData<List<Workout>> by lazy {
        MutableLiveData<List<Workout>>().also { liveData ->
            viewModelScope.launch {
                liveData.value = getAllWorkouts()
            }
        }
    }

    val exercises: LiveData<List<Exercise>> by lazy {
        MutableLiveData<List<Exercise>>().also { liveData ->
            viewModelScope.launch {
                liveData.value = getAllExercises()
            }
        }
    }

    private suspend fun getAllWorkouts(): List<Workout> {
        return withContext(Dispatchers.IO) {
            database.workoutDao().getAllWorkouts()
        }
    }

    private suspend fun getAllExercises(): List<Exercise> {
        return withContext(Dispatchers.IO) {
            database.workoutDao().getAllExercises()
        }
    }

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