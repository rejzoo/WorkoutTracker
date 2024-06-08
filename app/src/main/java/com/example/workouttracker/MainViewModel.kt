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
}