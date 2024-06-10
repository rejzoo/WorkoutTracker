package com.example.workouttracker.workouts

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.workouttracker.data.WorkoutWithExercises

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WorkoutsScreen(navigateToCreateWorkoutScreen: () -> Unit,
                           modifier: Modifier, painter: Painter,
                           workoutsViewModel: WorkoutsViewModel)
{
    val workoutsViewScope = rememberCoroutineScope()

    val workouts by workoutsViewModel.workouts.observeAsState(emptyList())
    val exercises by workoutsViewModel.exercises.observeAsState(emptyList())

    val workoutsWithExercises = workouts.map { workout ->
        val exercisesForWorkout = exercises.filter { it.workoutId == workout.id }
        WorkoutWithExercises(workout, exercisesForWorkout)
    }

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
            items(workoutsWithExercises) { workoutWithExercises ->
                WorkoutCard(workoutWithExercises)
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

@Composable
fun WorkoutCard(workoutWithExercises: WorkoutWithExercises) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = workoutWithExercises.workout.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = DarkYellow
                ),
            )
            workoutWithExercises.exercises.forEach { exercise ->
                Text(
                    text = buildAnnotatedString {
                        append("${exercise.name}\nSets ${exercise.goalSets}  Reps ${exercise.goalReps}")
                        if (exercise.goalWeight > 0) {
                            append("  Weight(KG): ${exercise.goalWeight}")
                        }
                    }
                )
            }
        }
    }
}