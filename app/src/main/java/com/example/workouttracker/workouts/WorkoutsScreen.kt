package com.example.workouttracker.workouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.workouttracker.R
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkYellow
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*

@Composable
fun WorkoutsScreen(navigateToCreateWorkoutScreen: () -> Unit,
                   modifier: Modifier, painter: Painter,
                   workoutsViewModel: WorkoutsViewModel) {
    val workoutsViewScope = rememberCoroutineScope()

    Surface (
        modifier = modifier
    ){
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            item {

            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {
                    workoutsViewScope.launch {
                        navigateToCreateWorkoutScreen()
                        workoutsViewModel.createNewWorkout()
                    }
                },
                shape = ShapeDefaults.Medium,
                containerColor = DarkYellow,
                modifier = Modifier
                    .padding(16.dp)
                    .size(85.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.plus_solid), "Add button.",
                    tint = Black
                )
            }
        }
    }
}