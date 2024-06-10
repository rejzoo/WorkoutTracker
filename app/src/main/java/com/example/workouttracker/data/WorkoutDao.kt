package com.example.workouttracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertWorkout(workout: Workout): Long

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Transaction
    @Query("SELECT * FROM Workouts")
    suspend fun getAllWorkoutsWithExercises(): List<WorkoutWithExercises>
}