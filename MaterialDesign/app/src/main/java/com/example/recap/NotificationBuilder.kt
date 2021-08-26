package com.example.recap

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService


const val CHANNEL_ID = "TestApplicationId"


object NotificationBuilder {

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "text",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Content desc"
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as
                        NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun sendTestNotification(context:Context) {
        // when you want to send the notification
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_battery_full_24)
        .setContentTitle("ContentTitle")
        .setContentText("The long description text...")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
         .build()

         NotificationManagerCompat.from(context).notify(1, notification)
    }
}