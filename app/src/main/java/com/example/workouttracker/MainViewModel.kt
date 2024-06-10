package com.example.workouttracker

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainViewModel : ViewModel() {
    fun handleSettingsButton(currentRoute: String?, navController: NavController)
    {
        if (currentRoute == WorkoutTrackerScreen.Settings.name) {
            navController.popBackStack()
        } else {
            navController.navigate(WorkoutTrackerScreen.Settings.name)
        }
    }

    fun showTopBar(currentRoute: String?): Boolean
    {
        val screensWithoutTopBar = listOf(
            WorkoutTrackerScreen.Welcome.name
        )

        return currentRoute !in screensWithoutTopBar
    }

    fun showBottomBar(currentRoute: String?): Boolean
    {
        val screensWithoutBottomBar = listOf(
            WorkoutTrackerScreen.Welcome.name,
            WorkoutTrackerScreen.Settings.name
        )

        return currentRoute !in screensWithoutBottomBar
    }

    fun getDescriptionFromRoute(route: String?): String
    {
        return when (route) {
            "Home" -> "Home Screen"
            "Workouts" -> "Plan or create workouts"
            "Statistics" -> "Statistics of your progress"
            "Exercise" -> "Start workout"
            "Settings" -> "Change settings"
            "You" -> "About you"
            else -> ""
        }
    }
}