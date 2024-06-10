package com.example.workouttracker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Exercises",
    foreignKeys = [ForeignKey(
        entity = Workout::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("workoutId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutId: Int,
    val name: String,
    val goalReps: Int,
    val goalSets: Int,
    val goalWeight: Double,
    val actualReps: Int,
    val actualSets: Int,
    val actualWeight: Double
)