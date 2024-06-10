package com.example.workouttracker.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.workouttracker.WorkoutTrackerScreen
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkYellow

@Composable
fun SettingsScreen(popBackStack: () -> Unit, modifier: Modifier,
                   painter: Painter) {
    Surface(
        modifier = Modifier.fillMaxSize().then(modifier),
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { popBackStack() },
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Black, containerColor = DarkYellow),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Apply",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(onClick = { popBackStack() },
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
            }
        }
    }
}