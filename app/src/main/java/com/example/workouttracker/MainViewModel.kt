package com.example.workouttracker

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class MainViewModel : ViewModel() {
    fun handleSettingsButton(currentRoute: String?, navController: NavHostController)
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

    fun showBottonBar(currentRoute: String?): Boolean
    {
        val screensWithoutBottomBar = listOf(
            WorkoutTrackerScreen.Welcome.name,
            WorkoutTrackerScreen.Settings.name
        )

        return currentRoute !in screensWithoutBottomBar
    }
}