package com.example.mobileassignmentone

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherLocationService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Initial placeholder notification while we fetch real data
        val loadingNotification = createNotification("Loading...", "Fetching weather data...")
        startForeground(1, loadingNotification)

        fetchAndNotify()
        return START_STICKY
    }

    private fun fetchAndNotify() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location == null) {
                val fallbackNotification = createNotification("Location Error", "Unable to get current location")
                startForeground(1, fallbackNotification)
                return@addOnSuccessListener
            }

            scope.launch {
                try {
                    val weather = WeatherFetcher.fetchWeatherByLocation(location.latitude, location.longitude)
                    val notification = createNotification(
                        weather.cityName,
                        "${weather.description}, ${weather.temperature}°C"
                    )
                    startForeground(1, notification)
                } catch (e: Exception) {
                    val errorNotification = createNotification("Error", "Failed to fetch weather data")
                    startForeground(1, errorNotification)
                }
            }
        }.addOnFailureListener {
            val failNotification = createNotification("Error", "Failed to get location")
            startForeground(1, failNotification)
        }
    }


    private fun createNotification(title: String, content: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, "weather_channel")
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_menu_compass) // ✅ Replace with your icon if desired
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "weather_channel",
                "Weather Updates",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows persistent weather info based on current location"
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
