package com.example.workouttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.workouttracker.home.HomeScreen
import com.example.workouttracker.settings.SettingsScreen
import com.example.workouttracker.trainings.TrainingScreen
import com.example.workouttracker.ui.theme.WorkoutTrackerTheme

enum class WorkoutTrackerScreen(@StringRes val title: Int, @DrawableRes val icon: Int) {
    Welcome(R.string.Welcome, R.drawable.dumbbell_solid),
    Settings(R.string.Settings, R.drawable.gear_solid),
    Home(R.string.Home, R.drawable.home),
    Trainings(R.string.Trainings, R.drawable.dumbbell_solid)
}

class Main : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            WorkoutTrackerTheme {

                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            if (mainViewModel.showTopBar(currentRoute)) {
                                TopAppBar(
                                    title = { Text(currentRoute.toString()) },
                                    actions = {
                                        IconButton(onClick = {
                                                mainViewModel.handleSettingsButton(currentRoute, navController)
                                            }
                                        ) {
                                            Icon(Icons.Default.Settings, "Settings",)
                                        }
                                    }
                                )
                            }
                        },
                        bottomBar = {
                            if (mainViewModel.showBottonBar(currentRoute)) {
                                BottomAppBar {
                                    NavigationBarButtons(navController)
                                }
                            }
                        }
                    ) { padding ->
                        NavHost(navController, WorkoutTrackerScreen.Welcome.name) {
                            composable(WorkoutTrackerScreen.Welcome.name) {
                                WelcomeScreen(navController, Modifier.padding(padding))
                            }
                            composable(WorkoutTrackerScreen.Settings.name) {
                                SettingsScreen(navController, Modifier.padding(padding),
                                                navController.currentBackStackEntry)
                            }
                            composable(WorkoutTrackerScreen.Home.name) {
                                HomeScreen(navController, Modifier.padding(padding))
                            }
                            composable(WorkoutTrackerScreen.Trainings.name) {
                                TrainingScreen(navController, Modifier.padding(padding))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Blue
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = { navController.navigate(WorkoutTrackerScreen.Home.name) },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
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

@Composable
fun NavigationBarButtons(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0)}

    val navigationScreens = listOf(
        WorkoutTrackerScreen.Home,
        WorkoutTrackerScreen.Trainings
    )

    NavigationBar {
        navigationScreens.forEachIndexed { index, screen ->
            NavigationBarItem(selected = selectedItem == index,
                            onClick = { selectedItem = index
                                        navController.navigate(screen.name) },
                            icon = {
                                Box(modifier = Modifier.size(24.dp))
                                {
                                    Icon(painterResource(screen.icon), screen.name) }
                                },
                            label = { Text(text = screen.name)}
            )
        }
    }
}