package com.example.mobileassignmentone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mobileassignmentone.ui.theme.MobileAssignmentOneTheme

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAssignmentOneTheme {
                AppNavGraph(viewModel)
            }
        }
    }
}