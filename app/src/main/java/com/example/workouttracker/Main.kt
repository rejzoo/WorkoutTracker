package com.example.workouttracker

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.workouttracker.exercise.ExerciseScreen
import com.example.workouttracker.home.HomeScreen
import com.example.workouttracker.settings.SettingsScreen
import com.example.workouttracker.statistics.StatisticsScreen
import com.example.workouttracker.ui.theme.Black
import com.example.workouttracker.ui.theme.DarkYellow
import com.example.workouttracker.ui.theme.WorkoutTrackerTheme
import com.example.workouttracker.welcome.WelcomeScreen
import com.example.workouttracker.welcome.WelcomeViewModel
import com.example.workouttracker.workouts.CreateWorkoutScreen
import com.example.workouttracker.workouts.CreateWorkoutViewModel
import com.example.workouttracker.workouts.WorkoutsScreen
import com.example.workouttracker.workouts.WorkoutsViewModel
import com.example.workouttracker.you.YouScreen

enum class WorkoutTrackerScreen(@StringRes val title: Int, @DrawableRes val icon: Int) {
    Welcome(R.string.Welcome, R.drawable.dumbbell_solid),
    Settings(R.string.Settings, R.drawable.gear_solid),
    Home(R.string.Home, R.drawable.home),
    Workouts(R.string.Workouts, R.drawable.dumbbell_solid),
    You(R.string.You, R.drawable.user_solid),
    Exercise(R.string.Exercise, R.drawable.start_solid),
    Statistics(R.string.Statistics, R.drawable.statistics_solid),
    CreateWorkout(R.string.Create, R.drawable.dumbbell_solid)
}

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val mainViewModel = MainViewModel()

        setContent {
            WorkoutTrackerTheme {

                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                val sharedPreferences = getSharedPreferences("user_goal", Context.MODE_PRIVATE)
                val backGround = painterResource(R.drawable.back_black)

                val welcomeViewModel = WelcomeViewModel(navController, sharedPreferences)
                val workoutsViewModel = WorkoutsViewModel(navController)
                val createWorkoutViewModel = CreateWorkoutViewModel(navController)

                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            if (mainViewModel.showTopBar(currentRoute)) {
                                TopBar(currentRoute, mainViewModel, navController)
                            }
                        },
                        bottomBar = {
                            if (mainViewModel.showBottonBar(currentRoute)) {
                                BottomAppBar (
                                    containerColor = Black
                                ) {
                                    NavigationBarButtons(navController)
                                }
                            }
                        }
                    ) { padding ->
                        NavHost(navController, WorkoutTrackerScreen.Welcome.name) {
                            composable(WorkoutTrackerScreen.Welcome.name) {
                                WelcomeScreen(welcomeViewModel, Modifier.padding(padding))
                            }
                            composable(WorkoutTrackerScreen.Settings.name) {
                                SettingsScreen(navController, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.Home.name) {
                                HomeScreen(navController, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.Workouts.name) {
                                WorkoutsScreen(workoutsViewModel, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.You.name) {
                                YouScreen(navController, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.Exercise.name) {
                                ExerciseScreen(navController, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.Statistics.name) {
                                StatisticsScreen(navController, Modifier.padding(padding), backGround)
                            }
                            composable(WorkoutTrackerScreen.CreateWorkout.name)
                            {
                                CreateWorkoutScreen(navController, createWorkoutViewModel, Modifier.padding(padding))
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentRoute: String?, mainViewModel: MainViewModel, navController: NavController) {
    TopAppBar(
        title = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentRoute.toString(),
                        color = DarkYellow,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    IconButton(onClick = { mainViewModel.handleSettingsButton(currentRoute, navController) }
                    ) {
                        Icon(
                            painterResource(R.drawable.gear_solid),
                            contentDescription = "Settings",
                            tint = DarkYellow,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 7.dp),
                    horizontalArrangement = Arrangement.Absolute.Left,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = mainViewModel.getDescriptionFromRoute(currentRoute),
                        color = DarkYellow,
                        fontSize = 14.sp
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Black
        )
    )
}

@Composable
fun NavigationBarButtons(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val navigationScreens = listOf(
        WorkoutTrackerScreen.Home,
        WorkoutTrackerScreen.Workouts,
        WorkoutTrackerScreen.Exercise,
        WorkoutTrackerScreen.Statistics,
        WorkoutTrackerScreen.You
    )

    NavigationBar(
        containerColor = Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            navigationScreens.forEachIndexed { index, screen ->
                val isSelected = selectedItem == index
                val iconTint = if (isSelected) DarkYellow else Color.White
                val textColor = if (isSelected) DarkYellow else Color.White

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            selectedItem = index
                            navController.navigate(screen.name)
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.name,
                        tint = iconTint,
                        modifier = Modifier.size(27.dp)
                    )
                    Text(
                        text = screen.name,
                        color = textColor,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}