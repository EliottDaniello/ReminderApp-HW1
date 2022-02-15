package com.codemave.reminderapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.codemave.reminderapp.ui.theme.MobileComputingTheme

class MainActivity : ComponentActivity()
{
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileComputingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ReminderApp(sharedPreferences)
                }

            }
        }
        sharedPreferences = getSharedPreferences("ReminderApp", Context.MODE_PRIVATE)
    }
}
