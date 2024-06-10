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

    @Query("SELECT MAX(id) FROM Workouts")
    suspend fun getMaxWorkoutId(): Int?

    @Query("DELETE FROM Workouts WHERE id = :id")
    suspend fun deleteWorkout(id: Int)

    @Query("DELETE FROM Exercises WHERE id = :id")
    suspend fun deleteExercise(id: Int)

    @Query("SELECT COUNT(*) FROM Exercises WHERE workoutId = :workoutId")
    suspend fun getNumberOfExercisesForWorkout(workoutId: Int): Int

    @Query("SELECT * FROM Exercises WHERE workoutId = :workoutId")
    suspend fun getExercisesForWorkout(workoutId: Int): List<Exercise>
}