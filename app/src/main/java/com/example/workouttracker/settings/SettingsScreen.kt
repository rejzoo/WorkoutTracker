package com.example.workouttracker.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.workouttracker.ui.theme.Black

@Composable
fun SettingsScreen(navController: NavHostController, modifier: Modifier, navBackStackEntry: NavBackStackEntry?) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier),
        color = Black,
    ) {
        Button(onClick = { navController.popBackStack() }
        ) {
            Text(text = "Apply")
        }
        Button(onClick = { navController.popBackStack() }
        ) {
            Text(text = "Cancel")
        }
    }
}