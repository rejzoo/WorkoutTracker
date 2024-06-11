package com.example.workouttracker

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.workouttracker.notifications.Notifications
import android.Manifest
import android.content.SharedPreferences
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit

class MainViewModel(pSharedPreferences: SharedPreferences) : ViewModel() {
    private var sharedPreferences = pSharedPreferences
    private val isFirstRun = mutableStateOf(sharedPreferences.getBoolean("first_run", true))

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
            WorkoutTrackerScreen.Settings.name,
            WorkoutTrackerScreen.CreateWorkout.name
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
            "CreateWorkout" -> "Add exercise"
            else -> ""
        }
    }

    fun requestPermission(context: Context, notifications: Notifications,
                          requestPermissionLauncher: ActivityResultLauncher<String>) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notifications.showNotification(
                notificationId = 1,
                title = "Enabled",
                contentText = "Notifications are enabled.",
                activityClass = Main::class.java)
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    fun getFirstRun(): Boolean
    {
        return isFirstRun.value
    }

    fun setFirstRunToFalse() {
        sharedPreferences.edit {
            putBoolean("first_run", false)
            apply()
        }
    }
}