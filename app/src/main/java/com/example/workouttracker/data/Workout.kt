package com.example.workouttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "Workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date
)

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }
}