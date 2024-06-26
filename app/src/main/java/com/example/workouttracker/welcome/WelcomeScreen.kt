package com.example.workouttracker.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workouttracker.R
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkYellow
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navigate: () -> Unit, welcomeViewModel: WelcomeViewModel, modifier: Modifier = Modifier) {
    var userInput by rememberSaveable { mutableStateOf(welcomeViewModel.getUserInput()) }
    val dateInput = welcomeViewModel.getInputDate()
    val firstRun = welcomeViewModel.getFirstRun()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        color = Black
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.gym_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f))
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.85f))
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Workout Tracker",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkYellow
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                if (!firstRun && userInput.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.85f))
                            .padding(15.dp)
                    ) {
                        Column (
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = welcomeViewModel.getUserInput(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkYellow
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = if (dateInput != 0L) DateFormat.getDateInstance()
                                    .format(Date(dateInput)) else "",
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))

                if (firstRun) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.85f))
                            .padding(10.dp)
                    ) {
                        OutlinedTextField(
                            value = userInput,
                            onValueChange = { userInput = it },
                            label = { Text("Enter your goal") },
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .padding(10.dp),
                            textStyle = TextStyle(color = DarkYellow),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = DarkYellow,
                                unfocusedBorderColor = DarkYellow,
                                disabledTextColor = DarkYellow,
                                focusedTextColor = DarkYellow,
                                focusedLabelColor = DarkYellow,
                                cursorColor = DarkYellow
                            )
                        )
                    }
                    Box(modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.85f))
                        .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "(Optional)\nThis goal will be your reminder why you started",
                            color = DarkYellow
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        welcomeViewModel.updateUserInput(userInput)
                        navigate()
                    },
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Black, containerColor = DarkYellow),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Launch",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}