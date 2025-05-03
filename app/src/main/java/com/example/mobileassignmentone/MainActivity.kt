package com.example.mobileassignmentone

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileassignmentone.ui.theme.MobileAssignmentOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val locationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            val notificationGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                permissions[Manifest.permission.POST_NOTIFICATIONS] == false else true

            if (locationGranted && notificationGranted) {
                startWeatherService()
            }
        }

        permissionLauncher.launch(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            else arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        )

        setContent {
            MobileAssignmentOneTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val weatherViewModel: WeatherViewModel = viewModel()
                    AppNavGraph(viewModel = weatherViewModel)
                }
            }
        }
    }

    private fun startWeatherService() {
        val intent = Intent(this, WeatherLocationService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}


