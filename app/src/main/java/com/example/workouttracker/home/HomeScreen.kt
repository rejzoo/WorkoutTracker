package com.example.workouttracker.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workouttracker.R
import com.example.workouttracker.ui.theme.DarkYellow

@Composable
fun HomeScreen(modifier: Modifier, painter: Painter) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        val imageSize = 300.dp
        val textColor = DarkYellow

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.benchpress),
                            contentDescription = "Bench Press",
                            modifier = Modifier.size(imageSize),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "Bench Press",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Strengthens the chest, shoulders, and triceps.",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "World Record: As of 2023, Julius Maddox holds the raw bench press record at 355 kg (782 lbs).",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "Description: The bench press is a fundamental upper-body exercise that primarily targets the pectoral muscles. It's a staple in many strength training routines.",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.squat),
                            contentDescription = "Squat",
                            modifier = Modifier.size(imageSize),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "Squat",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Targets the quads, glutes, and hamstrings.",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "World Record: As of 2023, Ray Williams holds the raw squat record at 490 kg (1,080 lbs).",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "Description: The squat is a foundational lower-body exercise that enhances strength and power in the legs and glutes. It's crucial for overall athletic performance.",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.deadlift),
                            contentDescription = "Deadlift",
                            modifier = Modifier.size(imageSize),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "Deadlift",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Engages the entire posterior chain including the back and legs.",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "World Record: As of 2023, Hafthor Bjornsson holds the record for the heaviest deadlift at 501 kg (1,104 lbs).",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "Description: The deadlift is a total-body strength exercise that emphasizes the muscles of the back, glutes, and hamstrings. It is considered one of the most effective exercises for overall strength development.",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}