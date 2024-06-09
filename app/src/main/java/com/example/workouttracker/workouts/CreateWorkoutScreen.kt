package com.example.workouttracker.workouts

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.workouttracker.R
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkGray
import com.example.workouttracker.ui.theme.DarkYellow
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun CreateWorkoutScreen(navController: NavHostController, workoutView: CreateWorkoutViewModel, modifier: Modifier) {
    var selectedType by rememberSaveable { mutableStateOf("") }
    var selectedExercise by rememberSaveable { mutableStateOf("") }
    var sets by rememberSaveable { mutableIntStateOf(0) }
    var reps by rememberSaveable { mutableIntStateOf(0) }
    var weight by rememberSaveable { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(scrollState),
        color = DarkGray
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Add Exercise",
                fontSize = 20.sp,
                color = DarkYellow
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExerciseDropdown(selectedType, selectedExercise,
                { selectedType = it }, { selectedExercise = it }, workoutView
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                NumberPicker(
                    value = sets,
                    onValueChange = { sets = it },
                    label = "Sets"
                )

                Spacer(modifier = Modifier.width(16.dp))

                NumberPicker(
                    value = reps,
                    onValueChange = { reps = it },
                    label = "Reps"
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                value = TextFieldValue("$weight"),
                onValueChange = { weight = it.text.toIntOrNull() ?: 0 },
                label = { Text("Weight (KG)", color = DarkYellow) },
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = TextStyle(color = Color.White, fontSize = 25.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Row {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Black, containerColor = DarkYellow),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        //workoutView.addExercise(selectedExercise, sets, reps, weight)
                        navController.popBackStack()
                    },
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Black, containerColor = DarkYellow),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Add",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseDropdown(selectedType: String, selectedExercise: String,
                     onTypeSelected: (String) -> Unit,
                     onExerciseSelected: (String) -> Unit,
                     workoutView: CreateWorkoutViewModel) {

    var expandedType by rememberSaveable { mutableStateOf(false) }
    var expandedExercise by rememberSaveable { mutableStateOf(false) }
    val showDialog = rememberSaveable { mutableStateOf(false) }

    Column {
        Box {
            TextButton(onClick = { expandedType = true }) {
                Text(
                    if (selectedType.isEmpty()) "Select type" else "Chosen type  ",
                    color = DarkYellow,
                    fontSize = 18.sp
                )
                Text(
                    text = selectedType,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            DropdownMenu(
                expanded = expandedType,
                onDismissRequest = { expandedType = false }
            ) {
                workoutView.typeOfExercise.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            onTypeSelected(type)
                            expandedType = false
                        },
                    )
                }
            }
        }

        Box {
            TextButton(onClick = { expandedExercise = true }) {
                Text(
                    if (selectedExercise.isEmpty()) "Select exercise" else "Selected exercise  ",
                    color = DarkYellow,
                    fontSize = 18.sp
                )
                Text(text = selectedExercise,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            if (selectedType.isEmpty() && expandedExercise) showDialog.value = true

            ShowAlertDialog(showDialog)

            DropdownMenu(
                expanded = expandedExercise,
                onDismissRequest = { expandedExercise = false }
            ) {
                workoutView.exercisesByType[selectedType]?.forEach { exercise ->
                    DropdownMenuItem(
                        text = { Text(exercise) },
                        onClick = {
                            onExerciseSelected(exercise)
                            expandedExercise = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NumberPicker(value: Int, onValueChange: (Int) -> Unit, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(label,
            color = DarkYellow,
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
        ) {
            IconButton(
                onClick = { if (value > 0) onValueChange(value - 1) },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(painterResource(R.drawable.minus_solid),
                    contentDescription = "Decrease",
                    tint = DarkYellow,
                    modifier = Modifier.size(30.dp)
                )
            }

            Text(
                text = value.toString(),
                modifier = Modifier.align(Alignment.TopCenter),
                fontSize = 33.sp
            )

            IconButton(
                onClick = { onValueChange(value + 1) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(painterResource(R.drawable.plus_solid),
                    contentDescription = "Increase",
                    tint = DarkYellow,
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(
                onClick = {onValueChange(0)},
                modifier = Modifier
                        .align(Alignment.BottomCenter)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun ShowAlertDialog(showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text("Info")
            },
            text = {
                Text("Select type first.")
            },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("OK")
                }
            }
        )
    }
}