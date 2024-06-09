package com.example.workouttracker.exercise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.workouttracker.ui.theme.Black

@Composable
fun ExerciseScreen(navController: NavHostController, modifier: Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier),
        color = Black,
    ) {

    }
}