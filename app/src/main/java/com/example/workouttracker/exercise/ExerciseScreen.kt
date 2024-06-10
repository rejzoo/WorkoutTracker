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

@Composable
fun ExerciseScreen(modifier: Modifier = Modifier, painter: Painter, exerciseViewModel: ExerciseViewModel) {
    var showOptions by rememberSaveable { mutableStateOf(false) }
    var startWorkout by rememberSaveable { mutableStateOf(false) }

    // prerobit selected workout na nieco jednoduchsie napr id alebo string pada preto
    var selectedWorkout by rememberSaveable { mutableStateOf<Workout?>(null) }
    val workouts by exerciseViewModel.workouts.observeAsState(emptyList())

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
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
                        onClick = { showOptions = true },
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

                if (showOptions) {

                    ButtonOptionsExample(
                        options = workouts,
                        onOptionSelected = { workout ->
                            selectedWorkout = workout
                            showOptions = false
                            startWorkout = true
                        }
                    )
                }

                if (startWorkout && selectedWorkout != null) {
                    WorkoutUI(exerciseViewModel)
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(options) { option ->
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

@Composable
fun WorkoutUI(exerciseViewModel: ExerciseViewModel) {
    Surface(
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Timer(exerciseViewModel)
            SetsAndReps(exerciseViewModel)
        }
    }
}

@Composable
fun Timer(exerciseViewModel: ExerciseViewModel) {
    var elapsedTime by rememberSaveable { mutableLongStateOf(0L) }
    var isTimerRunning by rememberSaveable { mutableStateOf(false) }

    /*
    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning) {
            delay(1000)
            elapsedTime++
        }
    }*/

    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = exerciseViewModel.formatTime(elapsedTime),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SetsAndReps(exerciseViewModel: ExerciseViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(DarkGray)
                        .padding(8.dp)
                        .size(120.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Sets",
                            fontSize = 25.sp,
                            color = DarkYellow
                        )
                    }
                    Text(
                        text = "B",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 50.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(DarkGray)
                        .padding(8.dp)
                        .size(120.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Reps",
                            fontSize = 25.sp,
                            color = DarkYellow
                        )
                    }
                    Text(
                        text = "0",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 50.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
