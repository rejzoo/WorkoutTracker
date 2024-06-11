package com.example.workouttracker.welcome

import android.content.SharedPreferences
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit
import androidx.lifecycle.ViewModel

class WelcomeViewModel(pSharedPreferences: SharedPreferences) : ViewModel() {
    private var sharedPreferences = pSharedPreferences
    private val userInput = mutableStateOf(sharedPreferences.getString("user_goal", "") ?: "")
    private val inputDate = mutableLongStateOf(sharedPreferences.getLong("input_date", 0L))
    private val isFirstRun = mutableStateOf(sharedPreferences.getBoolean("first_run", true))

    fun updateUserInput(input: String) {
        userInput.value = input
        inputDate.longValue = System.currentTimeMillis()
        saveDataToSharedPreferences()
    }

    fun getUserInput(): String
    {
        return userInput.value
    }

    fun getInputDate(): Long
    {
        return inputDate.longValue
    }

    fun getFirstRun(): Boolean
    {
        return isFirstRun.value
    }

    private fun saveDataToSharedPreferences() {
        sharedPreferences.edit {
            putString("user_goal", userInput.value)
            putLong("input_date", inputDate.longValue)
            putBoolean("first_run", false)
            apply()
        }
    }
}