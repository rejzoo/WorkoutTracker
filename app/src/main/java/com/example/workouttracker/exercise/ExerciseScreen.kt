package com.example.workouttracker.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workouttracker.data.Workout
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkGray
import com.example.workouttracker.ui.theme.DarkYellow
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExerciseScreen(modifier: Modifier = Modifier, painter: Painter,
                   exerciseViewModel: ExerciseViewModel = viewModel(),
                   navigateToCreateWorkoutScreen: () -> Unit) {

    var selectedWorkout by rememberSaveable { exerciseViewModel.selectedWorkout }
    var startWorkout by rememberSaveable { exerciseViewModel.startWorkout }
    var showOptions by rememberSaveable { exerciseViewModel.showOptions }
    val workouts by exerciseViewModel.workouts.observeAsState(emptyList())

    println("AA")
    println("AA" + exerciseViewModel.showOptions.value)
    println("AA" + exerciseViewModel.startWorkout.value)

    Surface(
        modifier = Modifier.fillMaxSize().then(modifier)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (!showOptions && !startWorkout) {
                                Button(
                                    onClick = {
                                        exerciseViewModel.showOptions.value = true
                                    },
                                    modifier = Modifier.height(60.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Black,
                                        containerColor = DarkYellow
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = "Select workout",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            if (exerciseViewModel.showOptions.value) {
                                ButtonOptionsExample(
                                    options = workouts,
                                    onOptionSelected = { workout ->
                                        selectedWorkout = workout.id
                                        showOptions = false
                                        startWorkout = true
                                    }
                                )
                            }

                            if (startWorkout && selectedWorkout != null) {
                                showOptions = false
                                startWorkout = false
                                navigateToCreateWorkoutScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonOptionsExample(
    options: List<Workout>,
    onOptionSelected: (Workout) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                options.forEach { option ->
                    Button(
                        onClick = { onOptionSelected(option) },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(70.dp)
                            .widthIn(min = 200.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Black,
                            containerColor = DarkYellow
                        ),
                    ) {
                        Text(
                            text = option.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
