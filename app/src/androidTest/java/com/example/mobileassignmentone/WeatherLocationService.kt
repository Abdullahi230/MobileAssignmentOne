package com.example.mobileassignmentone

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
        fetchAndNotify()
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
            location?.let {
                scope.launch {
                    val weather = WeatherFetcher.fetchWeatherByLocation(it.latitude, it.longitude)
                    showNotification(weather)
                }
            }
        }
    }

    private fun showNotification(weather: WeatherResponse) {
        val notification: Notification = NotificationCompat.Builder(this, "weather_channel")
            .setContentTitle("${weather.cityName}")
            .setContentText("${weather.description}, ${weather.temperature}°C")
            .setSmallIcon(R.drawable.sun)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(1, notification)
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "weather_channel",
            "Weather Updates",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Shows weather while using location"
        }
        val manager = Context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        fetchAndNotify()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}