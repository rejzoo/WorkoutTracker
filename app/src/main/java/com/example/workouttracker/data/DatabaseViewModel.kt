package com.example.workouttracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseViewModel(private val database: WorkoutDatabase) : ViewModel() {

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
}