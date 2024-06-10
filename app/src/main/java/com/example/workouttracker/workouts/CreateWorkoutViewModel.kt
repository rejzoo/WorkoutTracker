package com.example.workouttracker.workouts

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.workouttracker.data.WorkoutDatabase

class CreateWorkoutViewModel() : ViewModel() {
    val typeOfExercise = listOf("Push", "Pull", "Legs")

    val exercisesPush = listOf("Dumbbell bench press", "Lateral raises", "Incline dumbbell press",
        "Dip", "Overhead press", "Pushups", "Tricep pushdowns", "Chest fly",
        "Dumbbell shoulder press", "Close grip bench press", "Shoulder press",
        "Overhead Triceps Extension", "Incline push-up", "Dumbbell fly",
        "Triceps extensions", "Wide pushup", "Bench press", "Military press")

    val exercisesPull = listOf("Bent-over row", "Deadlift", "Lat pulldown", "Bicep curl",
        "One arm rows", "Pullups", "Dumbbell shrugs", "Dumbbell pullover", "Renegade row",
        "Face pull", "Hammer curls", "Row", "Chin ups", "Dumbbell row", "Preacher curl",
        "Concentration curl", "Inverted row", "T-bar row with handle", "Single leg RDL",
        "Cable row", "Dumbbell rows", "Good-morning", "Pendlay Row")


    val exercisesLegs = listOf(
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
}