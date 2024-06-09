package com.example.workouttracker.workouts

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.workouttracker.R

@Composable
fun WorkoutsScreen(navController: NavHostController, modifier: Modifier) {
    FloatingActionButton(
        onClick = { onClick() },
    ) {
        Icon(painterResource(R.drawable.plus_solid), "Add button.")
    }
}

fun onClick() {

}