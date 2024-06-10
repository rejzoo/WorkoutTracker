package com.example.workouttracker.workouts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.workouttracker.data.Exercise
import com.example.workouttracker.data.WorkoutDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class CreateWorkoutViewModel(private val database: WorkoutDatabase) : ViewModel() {

    private val exercisesPush = listOf("Dumbbell bench press", "Lateral raises", "Incline dumbbell press",
        "Dip", "Overhead press", "Pushups", "Tricep pushdowns", "Chest fly",
        "Dumbbell shoulder press", "Close grip bench press", "Shoulder press",
        "Overhead Triceps Extension", "Incline push-up", "Dumbbell fly",
        "Triceps extensions", "Wide pushup", "Bench press", "Military press")

    private val exercisesPull = listOf("Bent-over row", "Deadlift", "Lat pulldown", "Bicep curl",
        "One arm rows", "Pullups", "Dumbbell shrugs", "Dumbbell pullover", "Renegade row",
        "Face pull", "Hammer curls", "Row", "Chin ups", "Dumbbell row", "Preacher curl",
        "Concentration curl", "Inverted row", "T-bar row with handle", "Single leg RDL",
        "Cable row", "Dumbbell rows", "Good-morning", "Pendlay Row")


    private val exercisesLegs = listOf(
        "Squat", "Bulgarian split squat", "Leg curl", "Romanian deadlift", "Deadlift",
        "Front squat", "Leg press", "Lunge", "Reverse lunge", "Walking lunge", "Calf raises",
        "Goblet squat", "Standing calf raise", "Step-up", "Hack squat", "Leg extension",
        "Glute bridge", "Good-morning", "Barbell back squat", "Box jump", "Barbell hip thrust",
        "Kettlebell swing", "Single-leg skater squat")

    val exercisesByType = mapOf(
        "Push" to exercisesPush,
        "Pull" to exercisesPull,
        "Legs" to exercisesLegs
    )

    val typeOfExercise = listOf(
        "Push",
        "Pull",
        "Legs"
    )
    suspend fun addExercise(exercise: String, sets: Int, reps: Int, weight: Double): Boolean
    {
        if (exercise.isEmpty() || sets == 0 || reps == 0) {
            return false
        }

        val newExercise = Exercise(
            workoutId = getNextWorkoutId(),
            name = exercise,
            goalReps = reps,
            goalSets = sets,
            goalWeight = weight,
            actualReps = 0,
            actualSets = 0,
            actualWeight = 0.0
        )
        database.workoutDao().insertExercise(newExercise)
        return true
    }

    suspend fun createExercise(exercise: String, sets: Int, reps: Int, weight: Double): Exercise {
        return Exercise(
            workoutId = getNextWorkoutId(),
            name = exercise,
            goalReps = reps,
            goalSets = sets,
            goalWeight = weight,
            actualReps = 0,
            actualSets = 0,
            actualWeight = 0.0
        )
    }

    private suspend fun getNextWorkoutId(): Int {
        return withContext(Dispatchers.IO) {
            val maxId = database.workoutDao().getMaxWorkoutId() ?: 0
            maxId
        }
    }

    private suspend fun getActualWorkoutId(): Int {
        return withContext(Dispatchers.IO) {
            val maxId = database.workoutDao().getMaxWorkoutId() ?: 0
            maxId
        }
    }

    suspend fun deleteCurrentWorkout() {
        withContext(Dispatchers.IO) {
            val id = getActualWorkoutId()
            if (id > 0) {
                database.workoutDao().deleteWorkout(id)
            }
        }
    }

    suspend fun getListOfAllExercisesForCurrentWorkout(): List<Exercise>
    {
        return withContext(Dispatchers.IO) {
            database.workoutDao().getExercisesForWorkout(getActualWorkoutId())
        }
    }

    suspend fun deleteExercise(exercise: Exercise?) {
        withContext(Dispatchers.IO) {
            if (exercise != null) {
                database.workoutDao().deleteExercise(exercise.id)
            }
        }
    }
}