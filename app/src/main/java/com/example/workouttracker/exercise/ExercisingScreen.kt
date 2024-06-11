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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkGray
import com.example.workouttracker.ui.theme.DarkYellow

@Composable
fun ExercisingScreen(exerciseViewModel: ExerciseViewModel, painter: Painter,
                    modifier: Modifier) {

    var redrawSetsAndReps by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        color = Color.Transparent
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Timer(exerciseViewModel)
            SetsAndReps(exerciseViewModel, redrawSetsAndReps) {
                redrawSetsAndReps = false
            }
            Buttons(
                exerciseViewModel = exerciseViewModel,
                onRedraw = { redrawSetsAndReps = !redrawSetsAndReps }
            )
        }
    }
}

@Composable
fun Timer(exerciseViewModel: ExerciseViewModel) {
    var elapsedTime by rememberSaveable { mutableLongStateOf(0L) }
    var isTimerRunning by rememberSaveable { mutableStateOf(false) }

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
fun SetsAndReps(exerciseViewModel: ExerciseViewModel, redraw: Boolean,
                onRedrawComplete: () -> Unit) {
    if (redraw) {
        LaunchedEffect(Unit) {
            onRedrawComplete()
        }
    }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Exercise \n" + exerciseViewModel.getExerciseName(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
                        text = exerciseViewModel.getNumberOfSets(),
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
                        text = exerciseViewModel.getNumberOfReps(),
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 50.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Buttons(exerciseViewModel: ExerciseViewModel, onRedraw: () -> Unit) {
    Surface(
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        onRedraw()
                        exerciseViewModel.nextExercise()
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Black,
                        containerColor = DarkYellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Next exercise",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        exerciseViewModel.back()
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Black,
                        containerColor = DarkYellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Finish Workout",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        exerciseViewModel.back()
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Black,
                        containerColor = DarkYellow
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Cancel Workout",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}