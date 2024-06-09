package com.example.workouttracker.workouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.workouttracker.R
import com.example.workouttracker.ui.theme.Black

@Composable
fun WorkoutsScreen(navController: NavHostController, modifier: Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier),
        color = Black,
    ) {
        FloatingActionButton(
            onClick = { onClick() },
        ) {
            Icon(painterResource(R.drawable.plus_solid), "Add button.")
        }
    }
}

fun onClick() {

}