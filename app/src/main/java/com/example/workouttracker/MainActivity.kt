
package com.example.workouttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WorkouTracker()
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkouTracker() {
    NavigationBar()
}

@Composable
fun NavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {

        Button(
            onClick = { test() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
                .height(70.dp)
        ) {
        }

        Button(
            onClick = { test() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
                .height(70.dp)
        ) {
        }

        Button(
            onClick = { test() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
                .height(70.dp)
        ) {
        }

        Button(
            onClick = { test() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
                .height(70.dp)
        ) {
        }

        Button(
            onClick = { test() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
                .height(70.dp)
        ) {
        }
    }

    val texts = listOf("Home", "Plan", "Start", "Report", "You")

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        texts.forEach { text ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold // Set the text to bold
                )
            }
        }
    }
}

fun test()
{
    print("CLICK")
}
