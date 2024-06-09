package com.example.workouttracker.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import com.example.workouttracker.ui.theme.Black

@Composable
fun ExerciseScreen(navController: NavHostController, modifier: Modifier, painter: Painter) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}