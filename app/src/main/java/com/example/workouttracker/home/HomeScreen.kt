package com.example.workouttracker.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.workouttracker.ui.theme.Black

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier),
        color = Black,
    ) {

    }
}